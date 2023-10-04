package com.example.labfirebase

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.navigation.fragment.findNavController
import com.example.labfirebase.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginRegisterFragment : Fragment() {
    // Validity check flags
    private var passwordConfirmValid: Boolean = false
    private var passwordValid: Boolean = false
    private var usernameValid: Boolean = false
    // Flag indicating the state of the form. True means that the form is used for Login,
    // false means that the form is used to register user
    private var isLogin = true
    // Firebase Authentication variable
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        // To toggle between the login and register forms a loginRegisterToggle button is used
        binding.loginRegisterToggle.setOnClickListener {
            // Toggle the isLogin flag
            isLogin = !isLogin
            if (isLogin) {
                // When the isLogin is true:
                // Change the text on the login/register button to login
                binding.loginRegisterButton.setText(R.string.login_str)
                // Change the text on the login/register toggle button to register
                binding.loginRegisterToggle.setText(R.string.register_str)
                // Hide the password confirm field
                binding.loginPasswordConfirm.visibility = View.GONE
            } else {
                // When the isLogin is false:
                // Change the text on the login/register button to register
                binding.loginRegisterButton.setText(R.string.register_str)
                // Change the text on the login/register toggle button to login
                binding.loginRegisterToggle.setText(R.string.login_str)
                // Show the password confirm field
                binding.loginPasswordConfirm.visibility = View.VISIBLE
            }
            // Change the state of the login/register button depending on the state of the fields flags
            binding.loginRegisterButton.isEnabled = validate()
        }
        // The TextInputLayout is used in the form, which acts as a container for EditText and allows
        // for easier styling of the appearance of the view. Details on this view can be found on:
        // https://material.io/components/text-fields/android#using-text-fields
        // For each field in the form we set a onFocusChange listener for validating the
        // data input by the user
        binding.loginUsername.editText?.apply {
            // OnFocusChange for username:
            // Username is equivalent to an e-mail of the user
            setOnFocusChangeListener { v, hasFocus ->
                //We detect only the event of losing the focus on the View, which means that
                // the user has finished putting the data
                if (!hasFocus) {
                    // Get the username input by the user
                    val entry = this.text.toString()
                    // Clear the validity flag each time the focus changed for the username field
                    usernameValid = false
                    // Check the username only if it's not empty
                    if (entry.isNotEmpty()) {
                        // Check whether the username has a correct form
                        checkUserName(entry)
                        // Enable the login/register button when all the fields in the form are correct
                        binding.loginRegisterButton.isEnabled = validate()
                    }
                }
            }
        }
        binding.loginPassword.editText?.apply {
            // OnFocusChange for password:
            setOnFocusChangeListener { v, hasFocus ->
                //We detect only the event of losing the focus on the View, which means that
                // the user has finished putting the data
                if (!hasFocus) {
                    // Get the password input by the user
                    val entry = this.text.toString()
                    // Clear the validity flag each time the focus changed for the password field
                    passwordValid = false
                    // Check the password only if it's not empty
                    if (entry.isNotEmpty()) {
                        // Check whether the password has a correct form
                        checkPassword(entry)
                        // Enable the login/register button when all the fields in the form are correct
                        binding.loginRegisterButton.isEnabled = validate()
                    }
                }
            }
            // The password field is the last field in the Login form so we can change the keyboard
            // action button behaviour to DONE (indicating an end of the form). In the case that
            // the fragment is used to register (isLogin flag is false) the keyboard action button
            // is set to NEXT, which allows the user to change the focus to the next field in
            // the form. In this case this will be the password confirm field
            imeOptions = if (isLogin)
                EditorInfo.IME_ACTION_DONE
            else
                EditorInfo.IME_ACTION_NEXT
            // For the password field we also add an OnEditorActionListener to handle the DONE action
            setOnEditorActionListener { _, actionId, _ ->
                // Check whether the password has a correct form (if we the focus is still in the
                // field we won't validate the field with the OnFocusChanged)
                checkPassword(this.text.toString())
                when (actionId) {
                    // Handle the DONE action
                    EditorInfo.IME_ACTION_DONE -> {
                        // Check all the fields in the form
                        if (validate()) {
                            // If all data is valid:
                            // Enable login/register button
                            binding.loginRegisterButton.isEnabled = true
                            // Get the email and password input by the user
                            val email = binding.loginUsername.editText?.text.toString()
                            val password = binding.loginPassword.editText?.text.toString()
                            // Login with email and password
                            login(email, password)
                        }
                    }
                }
                false
            }
        }
        binding.loginPasswordConfirm.editText?.apply {
            // OnFocusChange for password confirm:
            setOnFocusChangeListener { v, hasFocus ->
                //We detect only the event of losing the focus on the View, which means that
                // the user has finished putting the data
                if (!hasFocus) {
                    // Get the password confirm input by the user
                    val entry = this.text.toString()
                    // Clear the validity flag each time the focus changed for the password confirm field
                    passwordConfirmValid = false
                    // Check the password confirm only if it's not empty
                    if (entry.isNotEmpty()) {
                        // Check whether the password and password confirm match
                        checkRegisterPassword(
                            entry,
                            binding.loginPassword.editText?.text.toString()
                        )
                        // Enable the login/register button when all the fields in the form are correct
                        binding.loginRegisterButton.isEnabled = validate()
                    }
                }
            }
            //The password confirm field is the last field in the Register form so we can change the keyboard
            // action button behaviour to DONE (indicating an end of the form).
            if (!isLogin)
                imeOptions = EditorInfo.IME_ACTION_DONE
            // Add an OnEditorActionListener to handle the DONE action
            setOnEditorActionListener { _, actionId, _ ->
                // Check whether the password and password confirm match
                checkRegisterPassword(
                    this.text.toString(),
                    binding.loginPassword.editText?.text.toString()
                )
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        // Check all the fields in the form
                        if (validate()) {
                            // If all data is valid:
                            // Enable login/register button
                            binding.loginRegisterButton.isEnabled = true
                            // Get the email and password input by the user
                            val email = binding.loginUsername.editText?.text.toString()
                            val password = binding.loginPassword.editText?.text.toString()
                            // Register with email and password
                            register(email, password)
                        }
                    }
                }
                false
            }
        }
        // Add an OnClickListener for the login/register button
        // The click on the button is only possible when all fields are validated
        binding.loginRegisterButton.setOnClickListener {
            // Get the email and password input by the user
            val email = binding.loginUsername.editText?.text.toString()
            val password = binding.loginPassword.editText?.text.toString()
            // Check the isLogin flag
            if (isLogin) {
                // Login with email and password when true
                login(email, password)
            } else {
                // Register with email and password when false
                register(email, password)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set isLogin to true
        isLogin = true
        // Initialize the Firebase authentication variable
        auth = Firebase.auth
        // The LoginRegisterFragment handles both the login and register process
        // The Login form is the default one so the password confirm field is hidden by default
        binding.loginPasswordConfirm.visibility = View.GONE
        // The Login/Register confirm button is disable until valid data is provided in the form
        binding.loginRegisterButton.isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        // Each time we start the app or navigate to the LoginRegisterFragment we check whether we
        // are already signed in.
        if (auth.currentUser != null) {
            //If the auth.currentUser isn't null we reload it's information and attach a listener
            // to the reload task
            auth.currentUser!!.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // When the reload is successful we can update the UI
                    updateUI(auth.currentUser)
                } else {
                    // Display a message when reload fails
                    Snackbar.make(binding.root, "Failed to reload user.", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validate(): Boolean {
        // Check the state of the fields flags. Depending on the isLogin flag we check either 2
        // or 3 flags (the passwordConfirmValid flag is not used in the Login form)
        return if (isLogin)
            passwordValid && usernameValid
        else
            passwordValid && usernameValid && passwordConfirmValid
    }

    private fun checkUserName(username: String) {
        // Check the username data
        Log.i("checkUserName", username)
        if (!isUserNameValid(username)) {
            // If the username fails the validity check set an error message in the loginUsername
            // TextInputField. This will change the end icon of the View and also display the
            // message in the Helper text region of the view
            binding.loginUsername.error = getString(R.string.invalid_username)
            // Set the usernameValid flag to false
            usernameValid = false
        } else {
            // If the username passes the test clear the error message and set the flag to true
            binding.loginUsername.error = ""
            usernameValid = true
        }
    }
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            // A valid username contains an "@" symbol and matches an email pattern
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            false
        }
    }

    private fun checkPassword(password: String) {
        // Check the password data
        Log.i("checkPassword", password)
        if (!isPasswordValid(password)) {
            // If the password fails the validity check set an error message in the loginPassword
            // TextInputField. This will change the end icon of the View and also display the
            // message in the Helper text region of the view
            binding.loginPassword.error = getString(R.string.invalid_password)
            // Set the passwordValid flag to false
            passwordValid = false
        } else {
            // If the password passes the test clear the error message and set the flag to true
            binding.loginPassword.error = ""
            passwordValid = true
        }
    }
    private fun isPasswordValid(password: String): Boolean {
        // A valid password has at least 6 characters
        return password.length >= 6
    }

    private fun checkRegisterPassword(passwordConfirm: String, password: String) {
        // Check the password confirm data
        Log.i("checkRegisterPassword", "$passwordConfirm $password")
        if (!isPasswordConfirmValid(passwordConfirm, password)) {
            // If the password confirm fails the validity check set an error message in the
            // loginPasswordConfirm TextInputField. This will change the end icon of the View and
            // also display the message in the Helper text region of the view
            binding.loginPasswordConfirm.error = getString(R.string.password_mismatch)
            // Set the passwordConfirmValid flag to false
            passwordConfirmValid = false
        } else {
            // If the password confirm passes the test clear the error message and set the flag to
            true
            binding.loginPasswordConfirm.error = ""
            passwordConfirmValid = true
        }
    }
    private fun isPasswordConfirmValid(passwordConfirm: String, password: String): Boolean {
        // Password and password confirm have to be the same
        return passwordConfirm == password
    }

    private fun login(email: String, password: String) {
        // Login process:
        // To sign in the app we use a signInWithEmailAndPassword method from the FirebaseAuth
        // variable and an OnCompleteListener to handle the result.
        // NOTE: This method of authentication has to be enabled in the project
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                // Check the status of the sign in task
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithEmail:success")
                    updateUI(auth.currentUser)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Snackbar.make(
                        binding.root, "Authentication failed.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun register(email: String, password: String) {
        // Login process:
        // To sign up in the app we use a createUserWithEmailAndPassword method from the FirebaseAuth
        // variable and an OnCompleteListener to handle the result.
        // NOTE: This method of authentication has to be enabled in the project
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                // Check the status of the sign ip task
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Register", "createUserWithEmail:success")
                    updateUI(auth.currentUser)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                    Snackbar.make(
                        binding.root, "Registering failed.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user ?: return
        // If the user variable isn't null display the message and navigate to the next destination
        // which is the SensorFragment
        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_LoginFragment_to_panel)
    }


}