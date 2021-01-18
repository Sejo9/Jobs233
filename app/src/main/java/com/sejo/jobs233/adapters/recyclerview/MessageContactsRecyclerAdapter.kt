package com.sejo.jobs233.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sejo.custodianlibrary.CircularImageView
import com.sejo.jobs233.R
import com.sejo.jobs233.models.data.inbox.MessageContactAdapterItem
import com.sejo.jobs233.network.BASE_SITE_URL
import com.squareup.picasso.Picasso

class MessageContactsRecyclerAdapter :
    ListAdapter<MessageContactAdapterItem, MessageContactsRecyclerAdapter.MCViewHolder>(
        MessageContactDiffCallback()
    ) {

    class MCViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val contactImage: CircularImageView = itemView.findViewById(R.id.contact_img)
        val contactName: TextView = itemView.findViewById(R.id.contact_name)
        val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        val lastMessageTime: TextView = itemView.findViewById(R.id.last_message_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MCViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_contact_list_item, parent, false)
        return MCViewHolder(view)
    }


    override fun onBindViewHolder(holder: MCViewHolder, position: Int) {
        val item = getItem(position)

        if (item.contact_img.isNotEmpty()) {
            Picasso.get().load(BASE_SITE_URL + item.contact_img).into(holder.contactImage)
        }

        holder.contactName.text = item.contact_name
        holder.lastMessage.text = item.last_message
        holder.lastMessageTime.text = item.last_message_time

        holder.itemView.setOnClickListener {
            val action = R.id.action_inboxFragment_to_messagingActivity
            Navigation.findNavController(it).navigate(action)
        }
    }


    class MessageContactDiffCallback : DiffUtil.ItemCallback<MessageContactAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: MessageContactAdapterItem,
            newItem: MessageContactAdapterItem
        ): Boolean {
            return oldItem.contact_name == newItem.contact_name
        }

        override fun areContentsTheSame(
            oldItem: MessageContactAdapterItem,
            newItem: MessageContactAdapterItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}