package ca.georgiancollege.ice6

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice6.databinding.ActivityMainBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create a reference to the ActivityMainBinding Class object
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val calculator = Calculator(binding)

//        val contactList = deserializeJSON()
//        if (contactList != null)
//        {
//            for(contact in contactList)
//            {
//                Log.i("listItem", contact.toString())
//            }
//        }

        for (contact in deserializeJSON()!!)
        {
            Log.i("listItem", contact.toString())
        }
    }

    private fun getTextFromResource(context: Context, resourceId: Int): String
    {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText()}
    }

   private fun getTextFromAsset(context: Context, fileName: String): String
    {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText()}
    }

    private fun deserializeJSON(): List<ContactModel>? // ? it may return null
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() // builder
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java) // defining the list type, contactMode -> List type
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)
        val contactListRawString = getTextFromResource(this, R.raw.contacts)
        Log.i("deserializeJSON", contactListRawString)
        val contactList: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList
    }

}


