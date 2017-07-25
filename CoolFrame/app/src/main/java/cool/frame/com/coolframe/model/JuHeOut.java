package cool.frame.com.coolframe.model;

import java.util.List;

/**
 * Created by rankaifeng on 2017/7/24.
 */

public class JuHeOut {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

   public class Result {
        private List<Data> data;

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
    }

    public class Data {
        private String id;
        private String title;
        private List<String> albums;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getAlbums() {
            return albums;
        }

        public void setAlbums(List<String> albums) {
            this.albums = albums;
        }
    }
}
