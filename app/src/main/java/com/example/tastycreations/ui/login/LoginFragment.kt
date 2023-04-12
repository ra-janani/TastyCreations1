package com.example.tastycreations.ui.login

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tastycreations.R
import com.example.tastycreations.databinding.FragmentLoginBinding

import com.example.tastycreations.ui.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.facebook.FacebookSdk;
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger;



class LoginFragment : Fragment() {

    private val TAG: String?="LoginActivity"
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth;
    //Google
    private lateinit var googleSignInClient : GoogleSignInClient




    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
       // updateUI(currentUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        FacebookSdk.sdkInitialize(getApplicationContext());

        auth = Firebase.auth
       val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

       googleSignInClient = GoogleSignIn.getClient(requireActivity() , gso)

        binding?.btnGoogle?.setOnClickListener {
            signInGoogle()
        }

        return binding.root

    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(context, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                /*val intent : Intent = Intent(requireActivity() , MainActivity::class.java)
                intent.putExtra("email" , account.email)
                intent.putExtra("name" , account.displayName)
                startActivity(intent)*/
                findNavController().navigate(R.id.action_loginFragment3_to_nav_home)
            }else{
                Toast.makeText(context, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val regButton=binding.register
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            val result = validateUI()
            if (result) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Login")
                builder.setMessage("Login Complete.")
                builder.setPositiveButton("OK") { dialog, which ->
                    // Do something when the "OK" button is clicked
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                    // Do something when the "Cancel" button is clicked
                }
                builder.show()
                loadingProgressBar.visibility = View.VISIBLE
                login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }

        regButton.setOnClickListener {
            val resultregister = validateUI()
            if (resultregister) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Registered")
                builder.setMessage("Register Complete.Please Login")
                builder.setPositiveButton("OK") { dialog, which ->
                    // Do something when the "OK" button is clicked
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                    // Do something when the "Cancel" button is clicked
                }
                builder.show()
                loadingProgressBar.visibility = View.VISIBLE
                reg(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
                loadingProgressBar.visibility = View.INVISIBLE
                binding.username.text.clear()
                binding.password.text.clear()
            }


        }
    }

    private fun reg(username: String, password: String) {
        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //    updateUI(null)
                }
            }
    }

    private fun login(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val bundle = Bundle().apply {
                        putSerializable("userid", auth.currentUser?.email)
                    }
                    findNavController().navigate(
                        R.id.action_loginFragment3_to_nav_home
                    )
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //   updateUI(null)
                }
            }
    }

    private fun validateUI():Boolean
    {
        var allClear: Boolean = true

        val usernameEditText=binding.username
        val passwordEditText=binding.password

        if (usernameEditText.text.isEmpty()) {
            usernameEditText.error = "Username cannot be empty"
            allClear=false
        }

        if (passwordEditText.text.isEmpty()) {
            passwordEditText.error = "password cannot be empty"
            allClear=false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(usernameEditText.text.toString()).matches()) {
            usernameEditText.setText("")
            usernameEditText.error = "Invalid Email"
            //Toast.makeText(context, "Enter a valid Email address", Toast.LENGTH_SHORT).show()
            allClear = false
        }
        if(!validatePassword( passwordEditText.text.toString()))
        {
            passwordEditText.error="password should contain atleast 1 number,1 special character, 1captial,min length8"
            allClear=false
        }
        return allClear
    }

    private fun validatePassword(password: String): Boolean {
        val valid: Boolean = true
        val passwordRegex =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@!$%^&*#])[A-Za-z\\d@!%^&*#]{8,}$".toRegex()
        return passwordRegex.matches(password)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}