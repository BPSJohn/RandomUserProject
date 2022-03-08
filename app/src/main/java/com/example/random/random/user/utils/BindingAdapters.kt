package com.example.random.random.user.utils

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.random.random.user.data.model.User
import com.example.random.random.user.ui.main.MainAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if(imgUrl != null)
    {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https")?.build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .override(350, 350)
                    .fitCenter())
            .into(imgView)
    }
}

@BindingAdapter("listUsers")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as MainAdapter
    adapter.submitList(data)
}

@BindingAdapter(value = ["fullTitle", "fullFirstName", "fullLastName"])
fun bindFullName(textView: TextView, title: String?, firstName: String?, lastName: String?)
{
    var fullName = ""
    if(title != null && firstName != null && lastName != null)
    {
        fullName = "<b>Name: </b>$title. $firstName $lastName"
        textView.text = fullName
    }

    else if(firstName != null && lastName != null)
    {
        fullName = "<b>Name: </b>$firstName $lastName"
    }

    else if (firstName != null)
    {
        fullName = "<b>Name: </b>$firstName"
    }

    else if(lastName != null)
    {
        fullName = "<b>Name: </b>$lastName"

    }

    else
    {
        fullName = "No name found"
    }

    textView.text = Html.fromHtml(fullName.replace("\\n", "<br>"))

}

@BindingAdapter("title", "firstName", "lastName")
fun bindTitleFirstName(textView: TextView, title: String?, firstName: String?, lastName: String?)
{
    if(title != null && firstName != null && lastName != null)
    {
        val titleFirstName = "$title. $firstName $lastName"
        textView.text = titleFirstName
    }
    else if (firstName != null && lastName != null)
    {
        val firstNameLastName = "$firstName $lastName"
        textView.text = firstNameLastName
    }
    else if(firstName != null)
    {
        textView.text = firstName
    }
    else if (lastName != null)
    {
        textView.text = lastName
    }
    else
    {
        val noName = "No name"
        textView.text = noName
    }

}

@BindingAdapter("email")
fun bindEmail(textView: TextView, email: String?)
{
    email?.let {
        val fullEmail = "<b>Email: </b>$email"
        textView.text = Html.fromHtml(fullEmail.replace("\\n", "<br>"))
    } ?: run {
        val noEmail = "User has no email"
        textView.text = noEmail
    }

}

@BindingAdapter("age")
fun bindAge(textView: TextView, age: String?)
{
    age?.let {
        val fullAge = "<b>Age: </b>$age"
        textView.text = Html.fromHtml(fullAge.replace("\\n", "<br>"))
    } ?: run {
        val noAge = "User has no age"
        textView.text = noAge
    }
}
