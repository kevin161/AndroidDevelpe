package com.gyz.androiddevelope.response_bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 多数网络返会都是使用 这个类
 * 配合AvatarConverter使用 正则转 Object avatar 为String
 */
public class PinsMainEntity implements Parcelable {


    private long pin_id;
    private long user_id;
    private long board_id;
    private long file_id;
    /**
     * farm : farm1
     * bucket : hbimg
     * key : a2945bdf440f492c5144d24eebe45f23b82f3ff84d81f-4vhC2L
     * type : image/jpeg /type=image/gif
     * width : 900
     * height : 1350
     * frames : 1
     */

    private PinsFileEntity file;
    private int media_type;
    private String source;
    private String link;
    private String raw_text;
    private String via;
    private String via_user_id;
    private String original;
    private String created_at;
    private int like_count;
    private int seq;
    private int comment_count;
    private int repin_count;
    private int is_private;
    private String orig_source;
    private boolean liked;

    /**
     * user_id : 17344184
     * username : 蓝枫芊柔
     * urlname : y0ogn4uezm
     * created_at : 1432372599
     * avatar : https
     */

    private PinsUserEntity user;
    /**
     * board_id : 24048815
     * user_id : 17344184
     * title : 爱好
     * description :
     * category_id : food_drink
     * seq : 5
     * pin_count : 1128
     * follow_count : 76
     * like_count : 3
     * created_at : 1432544067
     * updated_at : 1450779767
     * deleting : 0
     * is_private : 0
     * extra : {"cover":{"pin_id":"401845965"}}
     */

    private PinsBoardEntity board;

    public void setPin_id(long pin_id) {
        this.pin_id = pin_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setBoard_id(long board_id) {
        this.board_id = board_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    /**
     * user_id : 605533
     * username : 宁馨郁金香
     * urlname : xxf837568038
     * created_at : 1344088961
     * avatar : https
     */


    public void setFile(PinsFileEntity file) {
        this.file = file;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setRaw_text(String raw_text) {
        this.raw_text = raw_text;
    }


    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public void setIs_private(int is_private) {
        this.is_private = is_private;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setOrig_source(String orig_source) {
        this.orig_source = orig_source;
    }

    public void setUser(PinsUserEntity user) {
        this.user = user;
    }

    public void setBoard(PinsBoardEntity board) {
        this.board = board;
    }

    public long getPin_id() {
        return pin_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getBoard_id() {
        return board_id;
    }

    public long getFile_id() {
        return file_id;
    }

    public PinsFileEntity getFile() {
        return file;
    }

    public int getMedia_type() {
        return media_type;
    }

    public String getSource() {
        return source;
    }

    public String getLink() {
        return link;
    }

    public String getRaw_text() {
        return raw_text;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getVia_user_id() {
        return via_user_id;
    }

    public void setVia_user_id(String via_user_id) {
        this.via_user_id = via_user_id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getLike_count() {
        return like_count;
    }

    public int getSeq() {
        return seq;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public int getIs_private() {
        return is_private;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getOrig_source() {
        return orig_source;
    }

    public PinsUserEntity getUser() {
        return user;
    }

    public PinsBoardEntity getBoard() {
        return board;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.pin_id);
        dest.writeLong(this.user_id);
        dest.writeLong(this.board_id);
        dest.writeLong(this.file_id);
        dest.writeParcelable(this.file, flags);
        dest.writeInt(this.media_type);
        dest.writeString(this.source);
        dest.writeString(this.link);
        dest.writeString(this.raw_text);
        dest.writeString(this.via);
        dest.writeString(this.via_user_id);
        dest.writeString(this.original);
        dest.writeString(this.created_at);
        dest.writeInt(this.like_count);
        dest.writeInt(this.seq);
        dest.writeInt(this.comment_count);
        dest.writeInt(this.repin_count);
        dest.writeInt(this.is_private);
        dest.writeString(this.orig_source);
        dest.writeByte(liked ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.board, flags);
    }

    public PinsMainEntity() {
    }

    protected PinsMainEntity(Parcel in) {
        this.pin_id = in.readLong();
        this.user_id = in.readLong();
        this.board_id = in.readLong();
        this.file_id = in.readLong();
        this.file = in.readParcelable(PinsFileEntity.class.getClassLoader());
        this.media_type = in.readInt();
        this.source = in.readString();
        this.link = in.readString();
        this.raw_text = in.readString();
        this.via = in.readString();
        this.via_user_id = in.readString();
        this.original = in.readString();
        this.created_at = in.readString();
        this.like_count = in.readInt();
        this.seq = in.readInt();
        this.comment_count = in.readInt();
        this.repin_count = in.readInt();
        this.is_private = in.readInt();
        this.orig_source = in.readString();
        this.liked = in.readByte() != 0;
        this.user = in.readParcelable(PinsUserEntity.class.getClassLoader());
        this.board = in.readParcelable(PinsBoardEntity.class.getClassLoader());
    }

    public static final Creator<PinsMainEntity> CREATOR = new Creator<PinsMainEntity>() {
        @Override
        public PinsMainEntity createFromParcel(Parcel source) {
            return new PinsMainEntity(source);
        }

        @Override
        public PinsMainEntity[] newArray(int size) {
            return new PinsMainEntity[size];
        }
    };
}
