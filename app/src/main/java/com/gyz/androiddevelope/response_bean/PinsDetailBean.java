package com.gyz.androiddevelope.response_bean;

import java.util.List;

/**
 * 图片的详情介绍
 */
public class PinsDetailBean {

    private PinBean pin;
    private int err;
    private String msg;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PinBean getPin() {
        return pin;
    }

    public void setPin(PinBean pin) {
        this.pin = pin;
    }

    public static class PinBean {
        private String pin_id;
        private String user_id;
        private String board_id;
        private String file_id;
        /**
         * farm : farm1
         * bucket : hbimg
         * key : e4c1b2d3a1ce5dfc6734040e788de435e23add914078f-toYbsE
         * type : image/jpeg
         * width : 658
         * height : 1008
         * frames : 1
         * theme : E5E2E0
         */

        private FileBean file;
        private int media_type;
        private String source;
        private String link;
        private String raw_text;
        private TextMetaBean text_meta;
        private String via;
        private String via_user_id;
        private String original;
        private String created_at;
        private String like_count;
        private String comment_count;
        private String repin_count;
        private String is_private;
        private String orig_source;
        /**
         * user_id : 13068963
         * username : 空空小屋
         * urlname : ipbjb5zsun
         * created_at : 1397986738
         * avatar : 图片地址
         */

        private UserBean user;


        private BoardBean board;
        /**
         * user_id : 16794224
         * username : 菠菜东
         * urlname : e1c40ykxvp
         * created_at : 1422067957
         * avatar : 图片地址
         */

        private ViaUserBean via_user;


        private ViaPinBean via_pin;


        private OriginalPinBean original_pin;
        private boolean hide_origin;
        private boolean liked;
        private Object breadcrumb;
        private String category;


        private List<RepinsBean> repins;


        private List<LikesBean> likes;

        public String getPin_id() {
            return pin_id;
        }

        public void setPin_id(String pin_id) {
            this.pin_id = pin_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBoard_id() {
            return board_id;
        }

        public void setBoard_id(String board_id) {
            this.board_id = board_id;
        }

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public FileBean getFile() {
            return file;
        }

        public void setFile(FileBean file) {
            this.file = file;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getRaw_text() {
            return raw_text;
        }

        public void setRaw_text(String raw_text) {
            this.raw_text = raw_text;
        }

        public TextMetaBean getText_meta() {
            return text_meta;
        }

        public void setText_meta(TextMetaBean text_meta) {
            this.text_meta = text_meta;
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

        public String getLike_count() {
            return like_count;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getRepin_count() {
            return repin_count;
        }

        public void setRepin_count(String repin_count) {
            this.repin_count = repin_count;
        }

        public String getIs_private() {
            return is_private;
        }

        public void setIs_private(String is_private) {
            this.is_private = is_private;
        }

        public String getOrig_source() {
            return orig_source;
        }

        public void setOrig_source(String orig_source) {
            this.orig_source = orig_source;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public BoardBean getBoard() {
            return board;
        }

        public void setBoard(BoardBean board) {
            this.board = board;
        }

        public ViaUserBean getVia_user() {
            return via_user;
        }

        public void setVia_user(ViaUserBean via_user) {
            this.via_user = via_user;
        }

        public ViaPinBean getVia_pin() {
            return via_pin;
        }

        public void setVia_pin(ViaPinBean via_pin) {
            this.via_pin = via_pin;
        }

        public OriginalPinBean getOriginal_pin() {
            return original_pin;
        }

        public void setOriginal_pin(OriginalPinBean original_pin) {
            this.original_pin = original_pin;
        }

        public boolean isHide_origin() {
            return hide_origin;
        }

        public void setHide_origin(boolean hide_origin) {
            this.hide_origin = hide_origin;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public Object getBreadcrumb() {
            return breadcrumb;
        }

        public void setBreadcrumb(Object breadcrumb) {
            this.breadcrumb = breadcrumb;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<RepinsBean> getRepins() {
            return repins;
        }

        public void setRepins(List<RepinsBean> repins) {
            this.repins = repins;
        }

        public List<LikesBean> getLikes() {
            return likes;
        }

        public void setLikes(List<LikesBean> likes) {
            this.likes = likes;
        }

        public static class FileBean {
            private String farm;
            private String bucket;
            private String key;
            private String type;
            private int width;
            private int height;
            private int frames;
            private String theme;

            public String getFarm() {
                return farm;
            }

            public void setFarm(String farm) {
                this.farm = farm;
            }

            public String getBucket() {
                return bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getFrames() {
                return frames;
            }

            public void setFrames(int frames) {
                this.frames = frames;
            }

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }
        }

        public static class TextMetaBean {
        }

        public static class UserBean {
            private int user_id;
            private String username;
            private String urlname;
            private int created_at;
            private Avatar avatar;

            public void setAvatar(Avatar avatar) {
                this.avatar = avatar;
            }

            public Avatar getAvatar() {
                return avatar;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUrlname() {
                return urlname;
            }

            public void setUrlname(String urlname) {
                this.urlname = urlname;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }
        }

        public static class BoardBean {
            private int board_id;
            private int user_id;
            private String title;
            private String description;
            private String category_id;
            private int seq;
            private int pin_count;
            private int follow_count;
            private int like_count;
            private int created_at;
            private int updated_at;
            private int deleting;
            private int is_private;
            private Object extra;
            private boolean following;

            private List<PinsBean> pins;

            public int getBoard_id() {
                return board_id;
            }

            public void setBoard_id(int board_id) {
                this.board_id = board_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getPin_count() {
                return pin_count;
            }

            public void setPin_count(int pin_count) {
                this.pin_count = pin_count;
            }

            public int getFollow_count() {
                return follow_count;
            }

            public void setFollow_count(int follow_count) {
                this.follow_count = follow_count;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public int getDeleting() {
                return deleting;
            }

            public void setDeleting(int deleting) {
                this.deleting = deleting;
            }

            public int getIs_private() {
                return is_private;
            }

            public void setIs_private(int is_private) {
                this.is_private = is_private;
            }

            public Object getExtra() {
                return extra;
            }

            public void setExtra(Object extra) {
                this.extra = extra;
            }

            public boolean isFollowing() {
                return following;
            }

            public void setFollowing(boolean following) {
                this.following = following;
            }

            public List<PinsBean> getPins() {
                return pins;
            }

            public void setPins(List<PinsBean> pins) {
                this.pins = pins;
            }

            public static class PinsBean {
                private String pin_id;
                private String user_id;
                private String board_id;
                private String file_id;
                /**
                 * farm : farm1
                 * bucket : hbimg
                 * key : 8bd9be44424bb264a47b39920db60270675c59f1901f-g5sEMq
                 * type : image/jpeg
                 * width : 439
                 * height : 658
                 * frames : 1
                 */

                private FileBean file;
                private int media_type;
                private Object source;
                private Object link;
                private String raw_text;
                private TextMetaBean text_meta;
                private String via;
                private String via_user_id;
                private String original;
                private String created_at;
                private String like_count;
                private String comment_count;
                private String repin_count;
                private String is_private;
                private Object orig_source;

                public String getPin_id() {
                    return pin_id;
                }

                public void setPin_id(String pin_id) {
                    this.pin_id = pin_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getBoard_id() {
                    return board_id;
                }

                public void setBoard_id(String board_id) {
                    this.board_id = board_id;
                }

                public String getFile_id() {
                    return file_id;
                }

                public void setFile_id(String file_id) {
                    this.file_id = file_id;
                }

                public FileBean getFile() {
                    return file;
                }

                public void setFile(FileBean file) {
                    this.file = file;
                }

                public int getMedia_type() {
                    return media_type;
                }

                public void setMedia_type(int media_type) {
                    this.media_type = media_type;
                }

                public Object getSource() {
                    return source;
                }

                public void setSource(Object source) {
                    this.source = source;
                }

                public Object getLink() {
                    return link;
                }

                public void setLink(Object link) {
                    this.link = link;
                }

                public String getRaw_text() {
                    return raw_text;
                }

                public void setRaw_text(String raw_text) {
                    this.raw_text = raw_text;
                }

                public TextMetaBean getText_meta() {
                    return text_meta;
                }

                public void setText_meta(TextMetaBean text_meta) {
                    this.text_meta = text_meta;
                }

                public String getVia() {
                    return via;
                }

                public String getVia_user_id() {
                    return via_user_id;
                }

                public void setVia(String via) {
                    this.via = via;
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

                public String getLike_count() {
                    return like_count;
                }

                public void setLike_count(String like_count) {
                    this.like_count = like_count;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                public String getRepin_count() {
                    return repin_count;
                }

                public void setRepin_count(String repin_count) {
                    this.repin_count = repin_count;
                }

                public String getIs_private() {
                    return is_private;
                }

                public void setIs_private(String is_private) {
                    this.is_private = is_private;
                }

                public Object getOrig_source() {
                    return orig_source;
                }

                public void setOrig_source(Object orig_source) {
                    this.orig_source = orig_source;
                }

                public static class FileBean {
                    private String farm;
                    private String bucket;
                    private String key;
                    private String type;
                    private int width;
                    private int height;
                    private int frames;

                    public String getFarm() {
                        return farm;
                    }

                    public void setFarm(String farm) {
                        this.farm = farm;
                    }

                    public String getBucket() {
                        return bucket;
                    }

                    public void setBucket(String bucket) {
                        this.bucket = bucket;
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getFrames() {
                        return frames;
                    }

                    public void setFrames(int frames) {
                        this.frames = frames;
                    }
                }

                public static class TextMetaBean {
                }
            }
        }

        public static class ViaUserBean {
            private int user_id;
            private String username;
            private String urlname;
            private int created_at;
            private Avatar avatar;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUrlname() {
                return urlname;
            }

            public void setUrlname(String urlname) {
                this.urlname = urlname;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setAvatar(Avatar avatar) {
                this.avatar = avatar;
            }

            public Avatar getAvatar() {
                return avatar;
            }
        }

        public static class ViaPinBean {
            private String pin_id;
            private String user_id;
            private String board_id;
            private String file_id;
            /**
             * farm : farm1
             * bucket : hbimg
             * key : e4c1b2d3a1ce5dfc6734040e788de435e23add914078f-toYbsE
             * type : image/jpeg
             * width : 658
             * height : 1008
             * frames : 1
             * theme : E5E2E0
             */

            private FileBean file;
            private int media_type;
            private String source;
            private String link;
            private String raw_text;
            private TextMetaBean text_meta;
            private String via;
            private String via_user_id;
            private int original;
            private int created_at;
            private int like_count;
            private int comment_count;
            private int repin_count;
            private int is_private;
            private String orig_source;
            /**
             * board_id : 19513803
             * user_id : 16794224
             * title : 美女
             * description :
             * category_id : beauty
             * seq : 4
             * pin_count : 299
             * follow_count : 39
             * like_count : 1
             * created_at : 1426004683
             * updated_at : 1459242774
             * deleting : 0
             * is_private : 0
             * extra : null
             */

            private BoardBean board;

            public String getPin_id() {
                return pin_id;
            }

            public void setPin_id(String pin_id) {
                this.pin_id = pin_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getBoard_id() {
                return board_id;
            }

            public void setBoard_id(String board_id) {
                this.board_id = board_id;
            }

            public String getFile_id() {
                return file_id;
            }

            public void setFile_id(String file_id) {
                this.file_id = file_id;
            }

            public FileBean getFile() {
                return file;
            }

            public void setFile(FileBean file) {
                this.file = file;
            }

            public int getMedia_type() {
                return media_type;
            }

            public void setMedia_type(int media_type) {
                this.media_type = media_type;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getRaw_text() {
                return raw_text;
            }

            public void setRaw_text(String raw_text) {
                this.raw_text = raw_text;
            }

            public TextMetaBean getText_meta() {
                return text_meta;
            }

            public void setText_meta(TextMetaBean text_meta) {
                this.text_meta = text_meta;
            }

            public String getVia() {
                return via;
            }

            public String getVia_user_id() {
                return via_user_id;
            }

            public void setVia(String via) {
                this.via = via;
            }


            public void setVia_user_id(String via_user_id) {
                this.via_user_id = via_user_id;
            }

            public int getOriginal() {
                return original;
            }

            public void setOriginal(int original) {
                this.original = original;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public int getRepin_count() {
                return repin_count;
            }

            public void setRepin_count(int repin_count) {
                this.repin_count = repin_count;
            }

            public int getIs_private() {
                return is_private;
            }

            public void setIs_private(int is_private) {
                this.is_private = is_private;
            }

            public String getOrig_source() {
                return orig_source;
            }

            public void setOrig_source(String orig_source) {
                this.orig_source = orig_source;
            }

            public BoardBean getBoard() {
                return board;
            }

            public void setBoard(BoardBean board) {
                this.board = board;
            }

            public static class FileBean {
                private String farm;
                private String bucket;
                private String key;
                private String type;
                private int width;
                private int height;
                private int frames;
                private String theme;

                public String getFarm() {
                    return farm;
                }

                public void setFarm(String farm) {
                    this.farm = farm;
                }

                public String getBucket() {
                    return bucket;
                }

                public void setBucket(String bucket) {
                    this.bucket = bucket;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getFrames() {
                    return frames;
                }

                public void setFrames(int frames) {
                    this.frames = frames;
                }

                public String getTheme() {
                    return theme;
                }

                public void setTheme(String theme) {
                    this.theme = theme;
                }
            }

            public static class TextMetaBean {
                /**
                 * start : 9
                 * offset : 20
                 */

                private List<LinksBean> links;

                public List<LinksBean> getLinks() {
                    return links;
                }

                public void setLinks(List<LinksBean> links) {
                    this.links = links;
                }

                public static class LinksBean {
                    private int start;
                    private int offset;

                    public int getStart() {
                        return start;
                    }

                    public void setStart(int start) {
                        this.start = start;
                    }

                    public int getOffset() {
                        return offset;
                    }

                    public void setOffset(int offset) {
                        this.offset = offset;
                    }
                }
            }

            public static class BoardBean {
                private int board_id;
                private int user_id;
                private String title;
                private String description;
                private String category_id;
                private int seq;
                private int pin_count;
                private int follow_count;
                private int like_count;
                private int created_at;
                private int updated_at;
                private int deleting;
                private int is_private;
                private Object extra;

                public int getBoard_id() {
                    return board_id;
                }

                public void setBoard_id(int board_id) {
                    this.board_id = board_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public int getSeq() {
                    return seq;
                }

                public void setSeq(int seq) {
                    this.seq = seq;
                }

                public int getPin_count() {
                    return pin_count;
                }

                public void setPin_count(int pin_count) {
                    this.pin_count = pin_count;
                }

                public int getFollow_count() {
                    return follow_count;
                }

                public void setFollow_count(int follow_count) {
                    this.follow_count = follow_count;
                }

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }

                public int getDeleting() {
                    return deleting;
                }

                public void setDeleting(int deleting) {
                    this.deleting = deleting;
                }

                public int getIs_private() {
                    return is_private;
                }

                public void setIs_private(int is_private) {
                    this.is_private = is_private;
                }

                public Object getExtra() {
                    return extra;
                }

                public void setExtra(Object extra) {
                    this.extra = extra;
                }
            }
        }

        public static class OriginalPinBean {
            private String pin_id;
            private String user_id;
            private String board_id;
            private String file_id;
            /**
             * farm : farm1
             * bucket : hbimg
             * key : e4c1b2d3a1ce5dfc6734040e788de435e23add914078f-toYbsE
             * type : image/jpeg
             * width : 658
             * height : 1008
             * frames : 1
             */

            private FileBean file;
            private int media_type;
            private String source;
            private String link;
            private String raw_text;
            private TextMetaBean text_meta;
            private String via;
            private String via_user_id;
            private Object original;
            private int created_at;
            private int like_count;
            private int comment_count;
            private int repin_count;
            private int is_private;
            private String orig_source;
            /**
             * user_id : 8946023
             * username : ①花①世界
             * urlname : vxmzorydct
             * created_at : 1374803426
             * avatar : 图片地址
             */

            private UserBean user;
            /**
             * board_id : 13999403
             * user_id : 8946023
             * title : 美腿
             * description :
             * category_id : beauty
             * seq : 4
             * pin_count : 477
             * follow_count : 105
             * like_count : 0
             * created_at : 1387205950
             * updated_at : 1433649314
             * deleting : 0
             * is_private : 0
             * extra : null
             */

            private BoardBean board;

            public String getPin_id() {
                return pin_id;
            }

            public void setPin_id(String pin_id) {
                this.pin_id = pin_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getBoard_id() {
                return board_id;
            }

            public void setBoard_id(String board_id) {
                this.board_id = board_id;
            }

            public String getFile_id() {
                return file_id;
            }

            public void setFile_id(String file_id) {
                this.file_id = file_id;
            }

            public FileBean getFile() {
                return file;
            }

            public void setFile(FileBean file) {
                this.file = file;
            }

            public int getMedia_type() {
                return media_type;
            }

            public void setMedia_type(int media_type) {
                this.media_type = media_type;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getRaw_text() {
                return raw_text;
            }

            public void setRaw_text(String raw_text) {
                this.raw_text = raw_text;
            }

            public TextMetaBean getText_meta() {
                return text_meta;
            }

            public void setText_meta(TextMetaBean text_meta) {
                this.text_meta = text_meta;
            }


            public void setVia(String via) {
                this.via = via;
            }

            public String getVia() {
                return via;
            }

            public String getVia_user_id() {
                return via_user_id;
            }

            public void setVia_user_id(String via_user_id) {
                this.via_user_id = via_user_id;
            }

            public Object getOriginal() {
                return original;
            }

            public void setOriginal(Object original) {
                this.original = original;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public int getRepin_count() {
                return repin_count;
            }

            public void setRepin_count(int repin_count) {
                this.repin_count = repin_count;
            }

            public int getIs_private() {
                return is_private;
            }

            public void setIs_private(int is_private) {
                this.is_private = is_private;
            }

            public String getOrig_source() {
                return orig_source;
            }

            public void setOrig_source(String orig_source) {
                this.orig_source = orig_source;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public BoardBean getBoard() {
                return board;
            }

            public void setBoard(BoardBean board) {
                this.board = board;
            }

            public static class FileBean {
                private String farm;
                private String bucket;
                private String key;
                private String type;
                private int width;
                private int height;
                private int frames;

                public String getFarm() {
                    return farm;
                }

                public void setFarm(String farm) {
                    this.farm = farm;
                }

                public String getBucket() {
                    return bucket;
                }

                public void setBucket(String bucket) {
                    this.bucket = bucket;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getFrames() {
                    return frames;
                }

                public void setFrames(int frames) {
                    this.frames = frames;
                }
            }

            public static class TextMetaBean {
            }

            public static class UserBean {
                private int user_id;
                private String username;
                private String urlname;
                private int created_at;
                private Avatar avatar;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getUrlname() {
                    return urlname;
                }

                public void setUrlname(String urlname) {
                    this.urlname = urlname;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public void setAvatar(Avatar avatar) {
                    this.avatar = avatar;
                }

                public Avatar getAvatar() {
                    return avatar;
                }
            }

            public static class BoardBean {
                private int board_id;
                private int user_id;
                private String title;
                private String description;
                private String category_id;
                private int seq;
                private int pin_count;
                private int follow_count;
                private int like_count;
                private int created_at;
                private int updated_at;
                private int deleting;
                private int is_private;
                private Object extra;

                public int getBoard_id() {
                    return board_id;
                }

                public void setBoard_id(int board_id) {
                    this.board_id = board_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public int getSeq() {
                    return seq;
                }

                public void setSeq(int seq) {
                    this.seq = seq;
                }

                public int getPin_count() {
                    return pin_count;
                }

                public void setPin_count(int pin_count) {
                    this.pin_count = pin_count;
                }

                public int getFollow_count() {
                    return follow_count;
                }

                public void setFollow_count(int follow_count) {
                    this.follow_count = follow_count;
                }

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }

                public int getDeleting() {
                    return deleting;
                }

                public void setDeleting(int deleting) {
                    this.deleting = deleting;
                }

                public int getIs_private() {
                    return is_private;
                }

                public void setIs_private(int is_private) {
                    this.is_private = is_private;
                }

                public Object getExtra() {
                    return extra;
                }

                public void setExtra(Object extra) {
                    this.extra = extra;
                }
            }
        }

        public static class RepinsBean {
            private String pin_id;
            private String user_id;
            private String board_id;
            private String file_id;
            /**
             * farm : farm1
             * bucket : hbimg
             * key : e4c1b2d3a1ce5dfc6734040e788de435e23add914078f-toYbsE
             * type : image/jpeg
             * width : 658
             * height : 1008
             * frames : 1
             * theme : E5E2E0
             */

            private FileBean file;
            private int media_type;
            private String source;
            private String link;
            private String raw_text;
            private TextMetaBean text_meta;
            private String via;
            private String via_user_id;
            private int original;
            private int created_at;
            private int like_count;
            private int comment_count;
            private int repin_count;
            private int is_private;
            private String orig_source;
            /**
             * user_id : 18269528
             * username : 蓝研摄影
             * urlname : lboa1rkawh
             * created_at : 1451962444
             * avatar : 图片地址
             */

            private UserBean user;
            /**
             * board_id : 28762046
             * user_id : 18269528
             * title : 拿包包美女
             * description :
             * category_id : null
             * seq : 33
             * pin_count : 183
             * follow_count : 6
             * like_count : 0
             * created_at : 1458722654
             * updated_at : 1459409854
             * deleting : 0
             * is_private : 0
             * extra : null
             * pins : [{"pin_id":666791613,"user_id":18269528,"board_id":28762046,"file_id":88168961,"file":{"farm":"farm1","bucket":"hbimg","key":"7ac354e38f9c2cf4d6afa776322f5ce9d49e585b1812d-waElcw","type":"image/jpeg","width":524,"height":769,"frames":1},"media_type":0,"source":"girl13.com","link":"http://www.girl13.com/tag/black-silk-socks/2/","raw_text":"","text_meta":{},"via":664384409,"via_user_id":13068963,"original":661716626,"created_at":1459409853,"like_count":0,"comment_count":0,"repin_count":0,"is_private":0,"orig_source":null},{"pin_id":666791079,"user_id":18269528,"board_id":28762046,"file_id":88389388,"file":{"farm":"farm1","bucket":"hbimg","key":"96d79e5ee16174c1351b289e09275616a06f65c4258cf-ywtDmp","type":"image/jpeg","width":606,"height":876,"frames":1},"media_type":0,"source":"girl13.com","link":"http://www.girl13.com/tag/black-silk-socks/2/","raw_text":"","text_meta":{},"via":664383816,"via_user_id":13068963,"original":661716177,"created_at":1459409832,"like_count":0,"comment_count":0,"repin_count":0,"is_private":0,"orig_source":null},{"pin_id":666790973,"user_id":18269528,"board_id":28762046,"file_id":86802273,"file":{"farm":"farm1","bucket":"hbimg","key":"64c5c93573d5a6571205d7abf5314e11feda1c44ac11e-yeLp8R","type":"image/png","width":597,"height":757,"frames":1},"media_type":0,"source":"cn.iammari.com","link":"http://cn.iammari.com/product/detail.html?product_no=3116&cate_no=5&display_group=1","raw_text":"","text_meta":{},"via":662566387,"via_user_id":13068963,"original":533281910,"created_at":1459409828,"like_count":0,"comment_count":0,"repin_count":0,"is_private":0,"orig_source":null},{"pin_id":666790846,"user_id":18269528,"board_id":28762046,"file_id":79155648,"file":{"farm":"farm1","bucket":"hbimg","key":"cd0e04c49bdf46c2d689113248dd353dae7800f0c73d6-4pkyC3","type":"image/png","width":580,"height":819,"frames":1},"media_type":0,"source":null,"link":null,"raw_text":"","text_meta":{},"via":662567336,"via_user_id":13068963,"original":447331953,"created_at":1459409823,"like_count":0,"comment_count":0,"repin_count":0,"is_private":0,"orig_source":null}]
             */

            private BoardBean board;

            public String getPin_id() {
                return pin_id;
            }

            public void setPin_id(String pin_id) {
                this.pin_id = pin_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getBoard_id() {
                return board_id;
            }

            public void setBoard_id(String board_id) {
                this.board_id = board_id;
            }

            public String getFile_id() {
                return file_id;
            }

            public void setFile_id(String file_id) {
                this.file_id = file_id;
            }

            public FileBean getFile() {
                return file;
            }

            public void setFile(FileBean file) {
                this.file = file;
            }

            public int getMedia_type() {
                return media_type;
            }

            public void setMedia_type(int media_type) {
                this.media_type = media_type;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getRaw_text() {
                return raw_text;
            }

            public void setRaw_text(String raw_text) {
                this.raw_text = raw_text;
            }

            public TextMetaBean getText_meta() {
                return text_meta;
            }

            public void setText_meta(TextMetaBean text_meta) {
                this.text_meta = text_meta;
            }

            public void setVia(String via) {
                this.via = via;
            }

            public String getVia() {
                return via;
            }

            public String getVia_user_id() {
                return via_user_id;
            }

            public void setVia_user_id(String via_user_id) {
                this.via_user_id = via_user_id;
            }

            public int getOriginal() {
                return original;
            }

            public void setOriginal(int original) {
                this.original = original;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public int getRepin_count() {
                return repin_count;
            }

            public void setRepin_count(int repin_count) {
                this.repin_count = repin_count;
            }

            public int getIs_private() {
                return is_private;
            }

            public void setIs_private(int is_private) {
                this.is_private = is_private;
            }

            public String getOrig_source() {
                return orig_source;
            }

            public void setOrig_source(String orig_source) {
                this.orig_source = orig_source;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public BoardBean getBoard() {
                return board;
            }

            public void setBoard(BoardBean board) {
                this.board = board;
            }

            public static class FileBean {
                private String farm;
                private String bucket;
                private String key;
                private String type;
                private int width;
                private int height;
                private int frames;
                private String theme;

                public String getFarm() {
                    return farm;
                }

                public void setFarm(String farm) {
                    this.farm = farm;
                }

                public String getBucket() {
                    return bucket;
                }

                public void setBucket(String bucket) {
                    this.bucket = bucket;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getFrames() {
                    return frames;
                }

                public void setFrames(int frames) {
                    this.frames = frames;
                }

                public String getTheme() {
                    return theme;
                }

                public void setTheme(String theme) {
                    this.theme = theme;
                }
            }

            public static class TextMetaBean {
            }

            public static class UserBean {
                private int user_id;
                private String username;
                private String urlname;
                private int created_at;
                private Avatar avatar;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getUrlname() {
                    return urlname;
                }

                public void setUrlname(String urlname) {
                    this.urlname = urlname;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public void setAvatar(Avatar avatar) {
                    this.avatar = avatar;
                }

                public Avatar getAvatar() {
                    return avatar;
                }
            }

            public static class BoardBean {
                private int board_id;
                private int user_id;
                private String title;
                private String description;
                private Object category_id;
                private int seq;
                private int pin_count;
                private int follow_count;
                private int like_count;
                private int created_at;
                private int updated_at;
                private int deleting;
                private int is_private;
                private Object extra;
                /**
                 * pin_id : 666791613
                 * user_id : 18269528
                 * board_id : 28762046
                 * file_id : 88168961
                 * file : {"farm":"farm1","bucket":"hbimg","key":"7ac354e38f9c2cf4d6afa776322f5ce9d49e585b1812d-waElcw","type":"image/jpeg","width":524,"height":769,"frames":1}
                 * media_type : 0
                 * source : girl13.com
                 * link : http://www.girl13.com/tag/black-silk-socks/2/
                 * raw_text :
                 * text_meta : {}
                 * via : 664384409
                 * via_user_id : 13068963
                 * original : 661716626
                 * created_at : 1459409853
                 * like_count : 0
                 * comment_count : 0
                 * repin_count : 0
                 * is_private : 0
                 * orig_source : null
                 */

                private List<PinsBean> pins;

                public int getBoard_id() {
                    return board_id;
                }

                public void setBoard_id(int board_id) {
                    this.board_id = board_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public Object getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(Object category_id) {
                    this.category_id = category_id;
                }

                public int getSeq() {
                    return seq;
                }

                public void setSeq(int seq) {
                    this.seq = seq;
                }

                public int getPin_count() {
                    return pin_count;
                }

                public void setPin_count(int pin_count) {
                    this.pin_count = pin_count;
                }

                public int getFollow_count() {
                    return follow_count;
                }

                public void setFollow_count(int follow_count) {
                    this.follow_count = follow_count;
                }

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }

                public int getDeleting() {
                    return deleting;
                }

                public void setDeleting(int deleting) {
                    this.deleting = deleting;
                }

                public int getIs_private() {
                    return is_private;
                }

                public void setIs_private(int is_private) {
                    this.is_private = is_private;
                }

                public Object getExtra() {
                    return extra;
                }

                public void setExtra(Object extra) {
                    this.extra = extra;
                }

                public List<PinsBean> getPins() {
                    return pins;
                }

                public void setPins(List<PinsBean> pins) {
                    this.pins = pins;
                }

                public static class PinsBean {
                    private String pin_id;
                    private String user_id;
                    private String board_id;
                    private String file_id;
                    /**
                     * farm : farm1
                     * bucket : hbimg
                     * key : 7ac354e38f9c2cf4d6afa776322f5ce9d49e585b1812d-waElcw
                     * type : image/jpeg
                     * width : 524
                     * height : 769
                     * frames : 1
                     */

                    private FileBean file;
                    private int media_type;
                    private String source;
                    private String link;
                    private String raw_text;
                    private TextMetaBean text_meta;
                    private String via;
                    private String via_user_id;
                    private int original;
                    private int created_at;
                    private int like_count;
                    private int comment_count;
                    private int repin_count;
                    private int is_private;
                    private Object orig_source;

                    public String getPin_id() {
                        return pin_id;
                    }

                    public void setPin_id(String pin_id) {
                        this.pin_id = pin_id;
                    }

                    public String getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(String user_id) {
                        this.user_id = user_id;
                    }

                    public String getBoard_id() {
                        return board_id;
                    }

                    public void setBoard_id(String board_id) {
                        this.board_id = board_id;
                    }

                    public String getFile_id() {
                        return file_id;
                    }

                    public void setFile_id(String file_id) {
                        this.file_id = file_id;
                    }

                    public FileBean getFile() {
                        return file;
                    }

                    public void setFile(FileBean file) {
                        this.file = file;
                    }

                    public int getMedia_type() {
                        return media_type;
                    }

                    public void setMedia_type(int media_type) {
                        this.media_type = media_type;
                    }

                    public String getSource() {
                        return source;
                    }

                    public void setSource(String source) {
                        this.source = source;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public String getRaw_text() {
                        return raw_text;
                    }

                    public void setRaw_text(String raw_text) {
                        this.raw_text = raw_text;
                    }

                    public TextMetaBean getText_meta() {
                        return text_meta;
                    }

                    public void setText_meta(TextMetaBean text_meta) {
                        this.text_meta = text_meta;
                    }

                    public String getVia() {
                        return via;
                    }

                    public String getVia_user_id() {
                        return via_user_id;
                    }

                    public void setVia(String via) {
                        this.via = via;
                    }


                    public void setVia_user_id(String via_user_id) {
                        this.via_user_id = via_user_id;
                    }

                    public int getOriginal() {
                        return original;
                    }

                    public void setOriginal(int original) {
                        this.original = original;
                    }

                    public int getCreated_at() {
                        return created_at;
                    }

                    public void setCreated_at(int created_at) {
                        this.created_at = created_at;
                    }

                    public int getLike_count() {
                        return like_count;
                    }

                    public void setLike_count(int like_count) {
                        this.like_count = like_count;
                    }

                    public int getComment_count() {
                        return comment_count;
                    }

                    public void setComment_count(int comment_count) {
                        this.comment_count = comment_count;
                    }

                    public int getRepin_count() {
                        return repin_count;
                    }

                    public void setRepin_count(int repin_count) {
                        this.repin_count = repin_count;
                    }

                    public int getIs_private() {
                        return is_private;
                    }

                    public void setIs_private(int is_private) {
                        this.is_private = is_private;
                    }

                    public Object getOrig_source() {
                        return orig_source;
                    }

                    public void setOrig_source(Object orig_source) {
                        this.orig_source = orig_source;
                    }

                    public static class FileBean {
                        private String farm;
                        private String bucket;
                        private String key;
                        private String type;
                        private int width;
                        private int height;
                        private int frames;

                        public String getFarm() {
                            return farm;
                        }

                        public void setFarm(String farm) {
                            this.farm = farm;
                        }

                        public String getBucket() {
                            return bucket;
                        }

                        public void setBucket(String bucket) {
                            this.bucket = bucket;
                        }

                        public String getKey() {
                            return key;
                        }

                        public void setKey(String key) {
                            this.key = key;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public int getWidth() {
                            return width;
                        }

                        public void setWidth(int width) {
                            this.width = width;
                        }

                        public int getHeight() {
                            return height;
                        }

                        public void setHeight(int height) {
                            this.height = height;
                        }

                        public int getFrames() {
                            return frames;
                        }

                        public void setFrames(int frames) {
                            this.frames = frames;
                        }
                    }

                    public static class TextMetaBean {
                    }
                }
            }
        }

        public static class LikesBean {
            private int user_id;
            private String username;
            private String urlname;
            private int created_at;
            private Avatar avatar;
            private int liked_at;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUrlname() {
                return urlname;
            }

            public void setUrlname(String urlname) {
                this.urlname = urlname;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setAvatar(Avatar avatar) {
                this.avatar = avatar;
            }

            public Avatar getAvatar() {
                return avatar;
            }

            public int getLiked_at() {
                return liked_at;
            }

            public void setLiked_at(int liked_at) {
                this.liked_at = liked_at;
            }
        }
    }
}
