package edu.macalester.comp124.hw6;

import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.lang.Language;
import org.wikapidia.core.model.LocalPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Analyzes the overlap in popular concepts.
 * Experimental code for Shilad's intro Java course.
 * Note that you MUST correct WikAPIdiaWrapper.DATA_DIRECTORY before this works.
 *
 * @author Shilad Sen
 */
public class PopularArticleAnalyzer {
    private final WikAPIdiaWrapper wpApi;

    /**
     * Constructs a new analyzer.
     * @param wpApi
     */
    public PopularArticleAnalyzer(WikAPIdiaWrapper wpApi) {
        this.wpApi = wpApi;
    }



    /**
     * Returns the n most popular articles in the specified language.
     * @param language
     * @param n
     * @return
     */
    public List<LocalPage> getMostPopular(Language language, int n) {
        List<LocalPagePopularity> popList=new ArrayList<LocalPagePopularity>();//makes list
        int numLinks = 0;
        List <LocalPage> pagePopularity= (wpApi.getLocalPages(language));
        for(int i=0; i<pagePopularity.size(); i++) {//iterates through list to get the page
            LocalPage page=pagePopularity.get(i);
            numLinks = wpApi.getNumInLinks(page);
            LocalPagePopularity lPP=new LocalPagePopularity(page,numLinks);
            popList.add(lPP);

        }

        Collections.sort(popList); //sorts the list by popularity
        Collections.reverse(popList); //reverses to get it in the right order
        List<LocalPage> poplist2 =new ArrayList<LocalPage>();
        for(int i=0; i<n;i++){
            poplist2.add(popList.get(i).getPage());
        }



        return poplist2;


    }

    public static void main(String args[]) {
        Language simple = Language.getByLangCode("simple");

        // Change the path below to point to the parent directory on the lab computer
        // or laptop that holds the BIG "db" directory.
        WikAPIdiaWrapper wrapper = new WikAPIdiaWrapper();

        // TODO: Complete me for part 1.
        // construct a PopularArticleAnalyzer
        PopularArticleAnalyzer popAnalyze= new PopularArticleAnalyzer(wrapper);

        List<LocalPage> popList=popAnalyze.getMostPopular(simple, 20);

        for(int i=0; i<popList.size();i++){
            System.out.println(popList.get(i).getTitle());
        }

        // Print out the 20 most popular articles in the language.
        // United states should be #1
    }


}
