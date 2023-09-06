package com.Hecht.ar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.Hecht.ar.ModelClass.PostClass
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import com.Hecht.ar.databinding.ItemCommentBinding

class CommentAdapter(private val commentListener: CommentClickListener, val fileList: MutableList<PostClass>)
    : ListAdapter<PostClass, CommentAdapter.CommentViewHolder>(ShortCommentDiffUtil) {

    private val listShowReplies = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val data = fileList[position]
        holder.bind(data);
    }

    override fun getItemCount(): Int {
        return fileList.size
    }
    inner class CommentViewHolder(private val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {

        private fun initBinding(binding: ItemCommentBinding, shortComment: PostClass) {

            if(shortComment.flag) {
                binding.tvDate.text = shortComment.author
                binding.tvName.text = shortComment.post
            }
            else  {
                binding.tvName.visibility = View.GONE
                binding.imgUpload.visibility = View.VISIBLE
                Picasso.get().load(shortComment.post)
                    .placeholder(R.drawable.ic_baseline_image_24).into(binding.imgUpload)
            }
            //  binding.tvDate.text = "${shortComment.date} - ${shortComment.author}"
            if(shortComment.repliess != null) {
                if (shortComment.repliess.isNotEmpty() )
                    createNestedComment(binding, shortComment)
            }
            binding.btnReply.setOnClickListener {
                if(binding.btnMore.text.equals("Hide replies")) {
                    binding.btnMore.performClick()
                }
                val dialogCommentView = LayoutInflater.from(it.context).inflate(R.layout.dialog_comment, null, false)
                MaterialAlertDialogBuilder(it.context)
                    .setTitle("Post a comment")
                    .setView(dialogCommentView)
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("Send") { _,_ ->
                        val author: String = dialogCommentView.findViewById<EditText>(R.id.ti_author).text.toString()
                        val message: String = dialogCommentView.findViewById<EditText>(R.id.ti_message).text.toString()
                        commentListener.onSendComment(
                            author,
                            message,
                            shortComment.level + 1,
                            (shortComment.parentId ?: shortComment.id) as Int?,adapterPosition,shortComment.flag
                        )
                    }
                    .show()
            }
        }

        fun bind(shortComment: PostClass) {
            initBinding(binding, shortComment)
        }

        private fun createNestedComment(binding: ItemCommentBinding, shortComment: PostClass) {
            binding.btnMore.isVisible = true
            binding.btnMore.text = "Show ${shortComment.repliess.size} replies"
            binding.btnMore.setOnClickListener {
                if(!listShowReplies.contains(shortComment.id)) {
                    listShowReplies.add(shortComment.id)
                    binding.btnMore.text = "Hide replies"
                    shortComment.repliess.forEach { nestedComment ->
                        val newComment = ItemCommentBinding.inflate(LayoutInflater.from(binding.root.context), null, false)
                        initBinding(newComment, nestedComment)
                        binding.llReplies.addView(newComment.root)
                    }
                    binding.llReplies.isVisible = true
                } else {
                    binding.btnMore.text = "Show ${shortComment.repliess.size} replies"
                    listShowReplies.remove(shortComment.id)
                    binding.llReplies.removeAllViews()
                    binding.llReplies.isVisible = false
                }
            }
        }
    }

    object ShortCommentDiffUtil: DiffUtil.ItemCallback<PostClass>() {

        override fun areItemsTheSame(oldItem: PostClass, newItem: PostClass): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: PostClass, newItem: PostClass): Boolean {
            return oldItem == newItem
        }
    }

//    open class CommentClickListener(
//        private val sendComment: (author: String, message: String, level: Int, parentId: Int?) -> Unit
//    ) {
//        fun onSendComment(author: String, message: String, level: Int, parentId: Int? = null) =
//            sendComment(author, message, level, parentId)
//    }
}