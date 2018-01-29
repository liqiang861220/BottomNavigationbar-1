package me.sugarkawhi.mreader.data;

import java.util.List;

/**
 * 行信息
 * 1.内容 line
 * 2.起始 x y 坐标 相对于绘制内容的y
 * 3.每个字的坐标
 * <p>
 * Created by ZhaoZongyao on 2018/1/11.
 */

public class LineData {
    private List<LetterData> letters;
    //相对于 内容绘制区域来讲
    private float offsetY;
    private boolean isChapterName;

    public List<LetterData> getLetters() {
        return letters;
    }

    public void setLetters(List<LetterData> letters) {
        this.letters = letters;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public boolean isChapterName() {
        return isChapterName;
    }

    public void setChapterName(boolean chapterName) {
        isChapterName = chapterName;
    }

    /**
     * 保存每个字的位置
     */
    public static class LetterData {
        private char letter;
        private float offsetX;
        private float offsetY;

        public char getLetter() {
            return letter;
        }

        public void setLetter(char letter) {
            this.letter = letter;
        }

        public float getOffsetX() {
            return offsetX;
        }

        public void setOffsetX(float offsetX) {
            this.offsetX = offsetX;
        }

        public float getOffsetY() {
            return offsetY;
        }

        public void setOffsetY(float offsetY) {
            this.offsetY = offsetY;
        }

        @Override
        public String toString() {
            return letter + "";
        }
    }
}
