package dev.greenox.data

import androidx.annotation.StringRes

data class Email(
    val id: Long,
    val sender: Account,
    val recipients: List<Account> = emptyList(),
    @field:StringRes val subject: Int = -1,
    @field:StringRes val body: Int = -1,
    var mailbox: MailboxType = MailboxType.Inbox,
    var createdAt: Int = -1
)