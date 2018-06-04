package com.why.dianpin.question.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.why.dianpin.R;
import com.why.dianpin.question.bean.QuestionDetailItemBean;
import com.why.dianpin.user.bean.UserBean;
import com.why.dianpin.util.UIUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaoyueyue
 * @since 2018/5/7.
 */

public class QuestionDetailItemHolder extends RecyclerView.ViewHolder {

    private final TextView mAuthor;
    private final TextView mContent;
    private final TextView mDate;

    public QuestionDetailItemHolder(View itemView) {
        super(itemView);

        mAuthor = UIUtils.findView(itemView, R.id.tv_answer_author);
        mContent = UIUtils.findView(itemView, R.id.tv_answer_content);
        mDate = UIUtils.findView(itemView, R.id.tv_answer_time);
    }

    public void setData(QuestionDetailItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        final UserBean author = itemBean.answer.author;
        if (author == null) {
            return;
        }

        mAuthor.setText(author.username);
        mContent.setText(itemBean.answer.content);
        mDate.setText(dataFormat(itemBean.answer.timestamp));
    }

    private String dataFormat(long timestamp) {
        final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(timestamp));
    }
}
