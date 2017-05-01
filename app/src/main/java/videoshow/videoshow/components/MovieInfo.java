package videoshow.videoshow.components;


import videoshow.videoshow.R;
import videoshow.videoshow.HttpActivity;

public class MovieInfo {
    public HttpActivity mHttpActivity;

    public static String resourceIDList[] = new String[]{"nm0000115", "nm0000237", "nm0000151", "nm00001382",
            "nm0000314", "nm00001665", "nm0000173"};
    public String mTitle;
    public String mDescription;
    public static int[] imageIds = new int[]{
            R.drawable.nm0000115, R.drawable.nm0000237, R.drawable.nm0000151, R.drawable.nm00001382, R.drawable.nm0000314,
            R.drawable.nm00001665, R.drawable.nm0000173
    };
//    public static String movieLinks[];


    public int getImage(int index) {
        return imageIds[index];
    }
    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int[] getImageIds() {
        return imageIds;
    }

    public String[] getResourceIDList() {
        return resourceIDList;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }


}
