package com.why.dianpin.user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author xiaoyueyue
 * @since 2018/5/6.
 */

public class UserBean implements Parcelable {

    public int id;
    public int age;
    public int sex;
    public String username;
    public String password;

    public UserBean() {}

    public UserBean(int id, int age, int sex, String username, String password) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.username = username;
        this.password = password;
    }

    protected UserBean(Parcel in) {
        id = in.readInt();
        age = in.readInt();
        sex = in.readInt();
        username = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(age);
        dest.writeInt(sex);
        dest.writeString(username);
        dest.writeString(password);
    }

    public static UserBean fromJson(JSONObject json) {
        UserBean user = new UserBean();
        user.id = json.optInt("id");
        user.age = json.optInt("age");
        user.sex = json.optInt("sex");
        user.username = json.optString("username", "");
        user.password = json.optString("password", "");
        return user;
    }

    public static JSONObject toJson(UserBean user) {
        JSONObject json1 = new JSONObject();
        if (user != null) {
            try {
                json1.put("id", user.id);
                json1.put("username", user.username);
                json1.put("password", user.password);
                json1.put("age", user.age);
                json1.put("sex", user.sex);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
