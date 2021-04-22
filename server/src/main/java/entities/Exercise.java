package entities;

public class Exercise {
    private Activity activity;
    private String filePath;

    public Exercise(Activity activity, String filePath) {
        this.activity = activity;
        this.filePath = filePath;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
