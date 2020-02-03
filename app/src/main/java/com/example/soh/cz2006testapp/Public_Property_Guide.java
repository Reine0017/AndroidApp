/**
 * Public property guide fragment, reads a pdf file using a library.
 */

package com.example.soh.cz2006testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

import static com.example.soh.cz2006testapp.R.id.pdf_view_public_property_help_guide;

public class Public_Property_Guide extends Fragment implements OnPageChangeListener,OnLoadCompleteListener {

    private static final String TAG = Guest_Main.class.getSimpleName();
    public static final String SAMPLE_FILE = "Public Housing Guide.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;

    /**
     * Empty constructor for help guide
     */

    public Public_Property_Guide() {
        // Required empty public constructor
    }

    /**
     * Creates the fragment
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_public__property__guide, container, false);
        pdfView = (PDFView) view.findViewById(pdf_view_public_property_help_guide);
        displayFromAsset(SAMPLE_FILE);
        return view;
    }

    /**
     * External library
     * Reads the pdf file and adds action
     * @param assetFileName
     */

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                //.scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    /**
     * Changes the page number.
     * @param page
     * @param pageCount
     */

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }



}