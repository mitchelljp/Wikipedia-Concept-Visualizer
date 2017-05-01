package edu.macalester.comp124.hw6;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.model.LocalPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A row of boxes for local pages in some language.
 *
 * @author Shilad Sen
 */
public class LanguageBoxes extends GCompound {
    private static final int PADDING = 5;

    private final Language language;
    private final Color color;
    private final List<LocalPageBox> boxes = new ArrayList<LocalPageBox>();

    /**
     * Creates a new set of language boxes associated with some pages.
     * @param color
     * @param language
     * @param pages
     */
    public LanguageBoxes(Color color, Language language, List<LocalPage> pages) {
        this.color = color;
        this.language = language;
        GLabel label = new GLabel(language.toString());
        label.setColor(ColorPallete.FONT_COLOR);
        add(label, 0, 0);
        for (LocalPage lp : pages) {
            LocalPageBox box = new LocalPageBox(color, lp);
            double x = (box.getWidth() + PADDING) * boxes.size();
            double y = label.getHeight() + PADDING;
            add(box, x, y);
            boxes.add(box);
        }
    }

    /**
     * Highlights the specified pages (i.e. colors them "normal").
     * All other pages should be colored gray.
     * @param pages
     */
    public void highlightPages(List<LocalPage> pages) {//loops over and sets the boxes different colors depending on if they are null or not.

        for(LocalPageBox box:boxes){
            if(pages.contains(box.getPage())){
                box.setFillColor(color);
            }
            else{
                box.setFillColor(ColorPallete.FADE);
            }
        }
//        while(pages)
//        For highlightPages you'll need to check each box. Retrieve the LocalPage associated with the box and check if the passed-in list contains it. If it does, highlight it with the "normal" color. If not, color it gray. I've created a method to let you set the color of a LocalPageBox to help.
//
//                The unhighlight method will simply restore all boxes to their "normal" color.

    }

    /**
     * Colors all pages "normally."
     */
    public void unhighlight() {
        for(int i=0;i<boxes.size();i++){
            boxes.get(i).setFillColor(ColorPallete.FADE);
        }
    }

    public Language getLanguage() {
        return language;
    }

    public List<LocalPageBox> getBoxes() {
        return boxes;
    }

    /**
     * Returns the local page box at some location.
     * @param x
     * @param y
     * @return
     */
    public LocalPageBox getLocalBoxAt(double x, double y) {
        GPoint p = getLocation();
        Object tmp = getElementAt(x - p.getX(), y - p.getY());
        if (tmp instanceof LocalPageBox){
            return (LocalPageBox)tmp;
        }
        else {
            return null;
        }
    }
}
