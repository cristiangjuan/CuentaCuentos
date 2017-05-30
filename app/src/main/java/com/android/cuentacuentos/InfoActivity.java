package com.android.cuentacuentos;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Outline;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewOutlineProvider;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import utils.Constants;
import utils.MusicManager;

public class InfoActivity extends AppCompatActivity {

    /*
     ******************* Vistas *******************
     */
    /**
     * Botón de play
     */
    private ImageButton play_btn;
    /**
     * Botón de audio
     */
    private ImageButton music_btn;
    /**
     * Botón de vuelta al menú principal
     */
    private ImageButton back_btn;
    /**
     * Botón de más info
     */
    private ImageButton more_btn;
    /**
     * Botón de menos info
     */
    private ImageButton less_btn;
    /**
     * Botón de background
     */
    private ImageButton background_btn;
    /**
     * Botón de anterior autor
     */
    private ImageButton arrow_left_btn;
    /**
     * Botón de siguiente autor
     */
    private ImageButton arrow_right_btn;
    /**
     * Imagen del logo
     */
    private ImageView logoImage;
    /**
     * Cuadro de texto secundario
     */
    private TextView logoSubTextView;

    /*
     ******************* Layouts *******************
     *
     */
    /**
     * Layout principal
     */
    private RelativeLayout mainLayout;
    /**
     * Layout para el efecto reveal
     */
    protected FrameLayout revealLayout;
    /**
     * Layout de barra de color inferior
     */
    private RelativeLayout colorBarLayout;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutCurrent;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutNext;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutBook;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutIlus;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutProg;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutEscr;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutNarr;
    /**
     * Layout del crédito
     */
    private RelativeLayout logoTextLayoutMusic;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutCurrent;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutBook;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutIlus;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutProg;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutEscr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutNarr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoMainTextLayoutMusic;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutCurrent;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutBook;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutIlus;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutProg;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutEscr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutNarr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoFirstHiddenTextLayoutMusic;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutCurrent;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutBook;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutIlus;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutProg;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutEscr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutNarr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoSecondHiddenTextLayoutMusic;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutCurrent;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutBook;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutIlus;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutProg;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutEscr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutNarr;
    /**
     * Layout de la parte superior del crédito
     */
    private LinearLayout logoThirdHiddenTextLayoutMusic;
    /**
     * Layout de los stepsDots
     */
    private LinearLayout stepDotsLayout;
    /**
     * Stepdot ilustracion
     */
    private View stepDotBook;
    /**
     * Stepdot ilustracion
     */
    private View stepDotIlus;
    /**
     * Stepdot programación
     */
    private View stepDotProg;
    /**
     * Stepdot escritura
     */
    private View stepDotEscr;
    /**
     * Stepdot narración
     */
    private View stepDotNarr;
    /**
     * Stepdot narración
     */
    private View stepDotMusic;
    /**
     * Stepdot actual
     */
    private View stepDotCurrent;
    /**
     * Stepdot siguiente
     */
    private View stepDotNext;

    /*
     ******************* Animaciones *******************
     */
    /**
     * Animación del botón de música
     */
    private AnimationSet clickMusic;
    /**
     * Animación del bóton de back al pulsarlo
     */
    private Animation clickBack;
    /**
     * Animación aparición botón de música
     */
    private Animation showMusic;
    /**
     * Animación aparición botón de back
     */
    private Animation showBack;
    /**
     * Animación ocultación botón de música
     */
    private Animation hideMusic;
    /**
     * Animación ocultación botón de back
     */
    private Animation hideBack;
    /**
     * Animación para esconder el botón de play
     */
    private AnimationSet hidePlay;
    /**
     * Animación para mostrar el botón de play
     */
    private AnimationSet showPlay;
    /**
     * Animación para esconder el botón de more
     */
    private AnimationSet hideMore;
    /**
     * Animación para mostrar el botón de more
     */
    private AnimationSet showMore;
    /**
     * Animación para esconder el botón de less
     */
    private AnimationSet hideLess;
    /**
     * Animación para mostrar el botón de less
     */
    private AnimationSet showLess;
    /**
     * Animación para esconder el botón de more
     */
    private AnimationSet hideMoreHiddenInfo;
    /**
     * Animación para mostrar el botón de arrow left
     */
    private AnimationSet showArrowLeft;
    /**
     * Animación para ocultar el botón de arrow left
     */
    private AnimationSet hideArrowLeft;
    /**
     * Animación para mostrar el botón de arrow right
     */
    private AnimationSet showArrowRight;
    /**
     * Animación para ocultar el botón de arrow right
     */
    private AnimationSet hideArrowRight;
    /**
     * Animación para reajustar el tamaño del botón de fondo
     */
    private ScaleAnimation reduceBackgroundButton;
    /**
     * Animación para reajustar el tamaño del botón de fondo
     */
    private ScaleAnimation augmentBackgroundButton;
    /**
     * Animación ocultación de la barra de color inferior
     */
    private TranslateAnimation colorBarOutTransition;
    /**
     * Animación ocultación del texto principal del logo
     */
    private TranslateAnimation logoTextOutTransition;
    /**
     * Animación ocultación del subtexto del logo
     */
    private TranslateAnimation logoSubtextOutTransition;
    /**
     * Animación ocultación del logo
     */
    private TranslateAnimation logoImageOutTransition;
    /**
     * Animación ocultación del logo en sentido contrario
     */
    private TranslateAnimation logoImageOutTransitionReverse;
    /**
     * Animación que muestra la barra de color inferior
     */
    private TranslateAnimation colorBarInTransition;
    /**
     * Animación que muestra la barra el texto principal del logo
     */
    private TranslateAnimation logoTextInTransition;
    /**
     * Animación que muestra el subtexto del logo
     */
    private TranslateAnimation logoSubtextInTranstion;
    /**
     * Animación que muestra el logo
     */
    private TranslateAnimation logoImageInTransition;
    /**
     * Animación que muestra el autor del logo
     */
    private TranslateAnimation authorInTransition;
    /**
     * Animación que esconde el autor del logo
     */
    private TranslateAnimation authorOutTransition;
    /**
     * Animación que muestra el autor del logo
     */
    private TranslateAnimation authorInTransitionReverse;
    /**
     * Animación que esconde el autor del logo
     */
    private TranslateAnimation authorOutTransitionReverse;
    /**
     * Animación que esconde los stepDots
     */
    private TranslateAnimation stepDotsOutTransition;
    /**
     * Animación que muestra los stepDots
     */
    private TranslateAnimation stepDotsInTransition;
    /**
     * Reduce el logo al mostrar la info oculta de los créditos
     */
    private AnimationSet reduceLogoHiddenInfo;
    /**
     * Aumenta el logo al mostrar la info oculta de los créditos
     */
    private AnimationSet augmentLogoHiddenInfo;
    /**
     * Desplaza el logo para esconderlo
     */
    private TranslateAnimation translateLogoHiddenInfo;
    /**
     * Desplaza el logo para esconderlo reverse
     */
    private TranslateAnimation translateLogoHiddenInfoReverse;
    /**
     * Desplaza el texto del crédito para mostrar la info oculta
     */
    private TranslateAnimation translateMainTextHiddenInfo;
    /**
     * Desplaza el texto del crédito para mostrar la info oculta
     */
    private TranslateAnimation translateMainTextHiddenInfoReverse;
    /**
     * Desplaza el texto del crédito para mostrar la info oculta - web
     */
    private AnimationSet showFirstLineHiddenInfo;
    /**
     * Desplaza el texto del crédito para esconder la info oculta - web
     */
    private AnimationSet hideFirstLineHiddenInfo;
    /**
     * Desplaza el texto del crédito para mostrar la info oculta - tlf
     */
    private AnimationSet showThirdLineHiddenInfo;
    /**
     * Desplaza el texto del crédito para esnconder la info oculta - tlf
     */
    private AnimationSet hideThirdLineHiddenInfo;
    /**
     * Desplaza el texto del crédito para esnconder la info oculta - tlf
     */
    private AnimationSet hideThirdLineHiddenInfoForExit;
    /**
     * Desplaza el texto del crédito para mostrar la info oculta - mail
     */
    private AnimationSet showSecondLineHiddenInfo;
    /**
     * Desplaza el texto del crédito para ocultar la info oculta - mail
     */
    private AnimationSet hideSecondLineHiddenInfo;
    /**
     * Animación de latido de botón de música
     */
    private AnimationSet heartBeatMusic;
    /**
     * Animación de latido de botón de Back
     */
    private Animation heartBeatBack;
    /**
     * Animación de latido de botón de More
     */
    private Animation heartBeatMore;
    /**
     * Animación de latido de botón de arrowLeft
     */
    private Animation heartBeatArrowLeft;
    /**
     * Animación de latido de botón de arrowRight
     */
    private Animation heartBeatArrowRight;
    /**
     * Animación de latido de botón de bacgorund
     */
    private Animation heartBeatBackground;
    /*
     ******************* Flags, index... *******************
     */
    /**
     * Constante que controla si la música esta en off o en on.
     */
    private boolean music_on = Constants.Autoplay.MUSIC_INICIO;
    /**
     * Constante que controla no pulsar 2 veces el botón de play
     */
    private boolean btn_locked = false;
    /**
     * Controla si esta visible o no la info oculta de los créditos
     */
    private boolean is_hidden_info_visible = false;
    /**
     * Controla que la música siga sonando al terminar la actividad. En caso de pasar a
     * otro menú seguirá sonando.
     */
    private boolean continue_music = false;

    /**
     * Contexto
     */
    private Context mContext;
    /**
     * Control de gesto para esconder barras de status y navegación.
     */
    private GestureDetector gestureDetector;
    /**
     * Vista para gestionar los cambios de visibilidad de las barras de status y nav
     */
    private View mDecorView;

    /**
     * Índice para créditos
     */
    private int indexCredits = 0;
    /**
     * Array que contiene los layouts de los créditos
     */
    private ArrayList<RelativeLayout> arrayCredits;
    /**
     * Array que contiene los stepDots
     */
    private ArrayList<View> arrayStepDots;
    /**
     * Array que contiene los layouts del texto del logo
     */
    private ArrayList<LinearLayout> arrayMainText;
    /**
     * Array que contiene los layouts de la primera linea hidden info
     */
    private ArrayList<LinearLayout> arrayFirstLine;
    /**
     * Array que contiene los layouts de la segunda linea hidden info
     */
    private ArrayList<LinearLayout> arraySecondLine;
    /**
     * Array que contiene los layouts de la tercera linea hidden info
     */
    private ArrayList<LinearLayout> arrayThirdLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(Constants.Log.METHOD, "InfoActivity - onCreate");

        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.activity_info);

        //Cambiamos el color de la barra de status
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_dark, null));
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_dark));
        }

        continue_music = false;

        //Inicializamos el detector de gestos
        gestureDetector = new GestureDetector(mContext, new GestureListener());

        mDecorView = getWindow().getDecorView();

        mDecorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {

                boolean visible = (mDecorView.getSystemUiVisibility()
                        & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

                boolean visibleStatus = (mDecorView.getSystemUiVisibility()
                        & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0;

                Log.w(Constants.Log.CONTROLS, "InfoActivity - NavigationBar - "+visible);
                Log.w(Constants.Log.CONTROLS, "InfoActivity - StatusBar - "+visibleStatus);
            }
        });

        setContentView(R.layout.activity_info);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        revealLayout = (FrameLayout) findViewById(R.id.revealLayout);
        logoImage = (ImageView) findViewById(R.id.logo);
        stepDotsLayout = (LinearLayout) findViewById(R.id.step_dot_layout);
        logoTextLayoutCurrent = (RelativeLayout) findViewById(R.id.text_layout_book);
        logoTextLayoutNext = (RelativeLayout) findViewById(R.id.text_layout_ilus);
        logoTextLayoutBook = (RelativeLayout) findViewById(R.id.text_layout_book);
        logoTextLayoutIlus = (RelativeLayout) findViewById(R.id.text_layout_ilus);
        logoTextLayoutProg = (RelativeLayout) findViewById(R.id.text_layout_prog);
        logoTextLayoutEscr = (RelativeLayout) findViewById(R.id.text_layout_escr);
        logoTextLayoutNarr = (RelativeLayout) findViewById(R.id.text_layout_narr);
        logoTextLayoutMusic = (RelativeLayout) findViewById(R.id.text_layout_music);
        logoMainTextLayoutBook = (LinearLayout) findViewById(R.id.main_text_layout_book);
        logoMainTextLayoutCurrent = logoMainTextLayoutBook;
        logoMainTextLayoutIlus = (LinearLayout) findViewById(R.id.main_text_layout_ilus);
        logoMainTextLayoutProg = (LinearLayout) findViewById(R.id.main_text_layout_prog);
        logoMainTextLayoutEscr = (LinearLayout) findViewById(R.id.main_text_layout_escr);
        logoMainTextLayoutNarr = (LinearLayout) findViewById(R.id.main_text_layout_narr);
        logoMainTextLayoutMusic = (LinearLayout) findViewById(R.id.main_text_layout_music);
        logoFirstHiddenTextLayoutBook = (LinearLayout) findViewById(R.id.hidden_text_web_layout_book);
        logoFirstHiddenTextLayoutCurrent = logoFirstHiddenTextLayoutBook;
        logoFirstHiddenTextLayoutIlus = (LinearLayout) findViewById(R.id.hidden_text_web_layout_ilus);
        logoFirstHiddenTextLayoutProg = (LinearLayout) findViewById(R.id.hidden_text_web_layout_prog);
        logoFirstHiddenTextLayoutEscr = (LinearLayout) findViewById(R.id.hidden_text_web_layout_escr);
        logoFirstHiddenTextLayoutNarr = (LinearLayout) findViewById(R.id.hidden_text_web_layout_narr);
        logoFirstHiddenTextLayoutMusic = (LinearLayout) findViewById(R.id.hidden_text_web_layout_music);
        logoSecondHiddenTextLayoutBook = (LinearLayout) findViewById(R.id.hidden_text_mail_layout_book);
        logoSecondHiddenTextLayoutCurrent = logoSecondHiddenTextLayoutBook;
        logoSecondHiddenTextLayoutIlus = (LinearLayout) findViewById(R.id.hidden_text_mail_layout_ilus);
        logoSecondHiddenTextLayoutProg = (LinearLayout) findViewById(R.id.hidden_text_mail_layout_prog);
        logoSecondHiddenTextLayoutEscr = (LinearLayout) findViewById(R.id.hidden_text_mail_layout_escr);
        logoSecondHiddenTextLayoutNarr = (LinearLayout) findViewById(R.id.hidden_text_mail_layout_narr);
        logoSecondHiddenTextLayoutMusic = (LinearLayout) findViewById(R.id.hidden_text_songs_layout_music);
        logoThirdHiddenTextLayoutBook = (LinearLayout) findViewById(R.id.hidden_text_tlf_layout_book);
        logoThirdHiddenTextLayoutCurrent = logoThirdHiddenTextLayoutBook;
        logoThirdHiddenTextLayoutIlus = (LinearLayout) findViewById(R.id.hidden_text_tlf_layout_ilus);
        logoThirdHiddenTextLayoutProg = (LinearLayout) findViewById(R.id.hidden_text_tlf_layout_prog);
        logoThirdHiddenTextLayoutEscr = (LinearLayout) findViewById(R.id.hidden_text_tlf_layout_escr);
        logoThirdHiddenTextLayoutNarr = (LinearLayout) findViewById(R.id.hidden_text_tlf_layout_narr);
        logoThirdHiddenTextLayoutMusic = (LinearLayout) findViewById(R.id.hidden_text_license_layout_music);
        stepDotCurrent = (View) findViewById(R.id.step_dot_book);
        stepDotBook = (View) findViewById(R.id.step_dot_book);
        stepDotIlus = (View) findViewById(R.id.step_dot_ilus);
        stepDotNext = (View) findViewById(R.id.step_dot_prog);
        stepDotProg = (View) findViewById(R.id.step_dot_prog);
        stepDotEscr = (View) findViewById(R.id.step_dot_escr);
        stepDotNarr = (View) findViewById(R.id.step_dot_narr);
        stepDotMusic = (View) findViewById(R.id.step_dot_music);
        colorBarLayout = (RelativeLayout) findViewById(R.id.color_bar_layout);

        //Inicializamos el botón de música
        music_btn = (ImageButton) findViewById(R.id.btn_music);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Le damos forma circular
            ViewOutlineProvider outline_music_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_logo_btns);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            music_btn.setOutlineProvider(outline_music_btn);
            music_btn.setClipToOutline(true);

        }

        if (MusicManager.isMusic_on()) {

            setMusicIconOn();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_info, null));
            }
            else {
                music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off_info));
            }
        }
        else {

            setMusicIconOff();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_disabled_info, null));
            }
            else{
                music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on_info));
            }
        }

        //Inicializamos el botón de back
        back_btn = (ImageButton) findViewById(R.id.btn_back);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Le damos forma circular
            ViewOutlineProvider outline_back_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_logo_btns);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            back_btn.setOutlineProvider(outline_back_btn);
            back_btn.setClipToOutline(true);
        }

        //Inicializamos el botón de play
        play_btn = (ImageButton ) findViewById(R.id.play_button);

        //Inicializamos el botón de more
        more_btn = (ImageButton ) findViewById(R.id.more_button);

        //Inicializamos el botón de more
        less_btn = (ImageButton ) findViewById(R.id.less_button);

        //Inicializamos el botón de more
        arrow_left_btn = (ImageButton ) findViewById(R.id.arrow_left_btn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewOutlineProvider outline_left_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_arrow_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            arrow_left_btn.setOutlineProvider(outline_left_btn);
            arrow_left_btn.setClipToOutline(true);
        }


        //Inicializamos el botón de more
        arrow_right_btn = (ImageButton ) findViewById(R.id.arrow_right_btn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewOutlineProvider outline_right_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_arrow_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            arrow_right_btn.setOutlineProvider(outline_right_btn);
            arrow_right_btn.setClipToOutline(true);
        }

        //Inicializamos el botón de more
        background_btn = (ImageButton ) findViewById(R.id.background_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ViewOutlineProvider outline_background = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            background_btn.setOutlineProvider(outline_background);
            background_btn.setClipToOutline(true);
        }

        //En caso de que haya habido un onStop justo antes de entrar
        MusicManager.createMusicRestart(true);

        prepareOnClicks();
        prepareCredits();
        prepareAnimations();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            startShowLayout();
        }
        else {
            startShowLayoutBL();
        }
    }

    private void prepareOnClicks() {
        //OnClicks
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - onClick backButton");

                if (!areButtonsLocked()) {

                    lockButtons();

                    back_btn.startAnimation(clickBack);
                    exitAnimations();
                }
            }
        });

        music_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - onClick musicButton");

                if (!areButtonsLocked()) {

                    //Bloqueamos el botón de play y el tap
                    lockButtons();

                    //Paramos animación latido y la reanudamos cuando termina de la animación de cambio de icono
                    music_btn.clearAnimation();
                    music_btn.startAnimation(clickMusic);

                    //Programamos desbloqueo
                    programUnlock(clickMusic.getDuration());

                    //Animación transformación voice on/off
                    //On - > Off
                    if (MusicManager.isMusic_on()) {

                        AnimationDrawable frameAnimation;
                        TransitionDrawable transition;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_on_off, null);

                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off_info, null);
                        }
                        else {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_on_off);

                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off_info);
                        }

                        music_btn.setBackground(transition);
                        music_btn.setImageDrawable(frameAnimation);
                        transition.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));
                        frameAnimation.start();

                        //Quitamos volumen de la música
                        MusicManager.setMusicVolumeOff();
                    }
                    //Off - > On
                    else {

                        AnimationDrawable frameAnimation;
                        TransitionDrawable transition;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_off_on, null);
                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on_info, null);
                        }
                        else {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_off_on);
                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on_info);
                        }

                        music_btn.setImageDrawable(frameAnimation);
                        music_btn.setBackground(transition);
                        transition.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));
                        frameAnimation.start();

                        //Restablecemos volumen de la música
                        MusicManager.setMusicVolumeOn();
                    }
                }
            }
        });

        more_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - onClick moreButton");

                if (!areButtonsLocked()) {

                    lockButtons();

                    is_hidden_info_visible = true;
                    showHiddenInfo();
                }
            }
        });

        less_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - onClick lessButton");

                if (!areButtonsLocked()) {

                    lockButtons();

                    is_hidden_info_visible = false;
                    hideHiddenInfo();
                }
            }
        });

        arrow_right_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - onClick moreButton");

                if (!areButtonsLocked()) {

                    lockButtons();

                    nextCredit();

                    //Programamos desbloqueo
                    programUnlock(authorInTransition.getDuration());
                }
            }
        });

        arrow_left_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - onClick moreButton");

                if (!areButtonsLocked()) {

                    lockButtons();

                    previousCredit();

                    //Programamos desbloqueo
                    programUnlock(authorInTransitionReverse.getDuration());
                }
            }
        });
    }

    /**
     * Prepara los créditos y los stepDots
     */
    private void prepareCredits(){

        arrayCredits = new ArrayList<RelativeLayout>();

        arrayCredits.add(logoTextLayoutBook);
        arrayCredits.add(logoTextLayoutIlus);
        arrayCredits.add(logoTextLayoutProg);
        arrayCredits.add(logoTextLayoutEscr);
        arrayCredits.add(logoTextLayoutNarr);
        arrayCredits.add(logoTextLayoutMusic);

        arrayStepDots = new ArrayList<View>();

        arrayStepDots.add(stepDotBook);
        arrayStepDots.add(stepDotIlus);
        arrayStepDots.add(stepDotProg);
        arrayStepDots.add(stepDotEscr);
        arrayStepDots.add(stepDotNarr);
        arrayStepDots.add(stepDotMusic);

        arrayMainText = new ArrayList<LinearLayout>();

        arrayMainText.add(logoMainTextLayoutBook);
        arrayMainText.add(logoMainTextLayoutIlus);
        arrayMainText.add(logoMainTextLayoutProg);
        arrayMainText.add(logoMainTextLayoutEscr);
        arrayMainText.add(logoMainTextLayoutNarr);
        arrayMainText.add(logoMainTextLayoutMusic);

        arrayFirstLine = new ArrayList<LinearLayout>();

        arrayFirstLine.add(logoFirstHiddenTextLayoutBook);
        arrayFirstLine.add(logoFirstHiddenTextLayoutIlus);
        arrayFirstLine.add(logoFirstHiddenTextLayoutProg);
        arrayFirstLine.add(logoFirstHiddenTextLayoutEscr);
        arrayFirstLine.add(logoFirstHiddenTextLayoutNarr);
        arrayFirstLine.add(logoFirstHiddenTextLayoutMusic);

        arraySecondLine = new ArrayList<LinearLayout>();

        arraySecondLine.add(logoSecondHiddenTextLayoutBook);
        arraySecondLine.add(logoSecondHiddenTextLayoutIlus);
        arraySecondLine.add(logoSecondHiddenTextLayoutProg);
        arraySecondLine.add(logoSecondHiddenTextLayoutEscr);
        arraySecondLine.add(logoSecondHiddenTextLayoutNarr);
        arraySecondLine.add(logoSecondHiddenTextLayoutMusic);

        arrayThirdLine = new ArrayList<LinearLayout>();

        arrayThirdLine.add(logoThirdHiddenTextLayoutBook);
        arrayThirdLine.add(logoThirdHiddenTextLayoutIlus);
        arrayThirdLine.add(logoThirdHiddenTextLayoutProg);
        arrayThirdLine.add(logoThirdHiddenTextLayoutEscr);
        arrayThirdLine.add(logoThirdHiddenTextLayoutNarr);
        arrayThirdLine.add(logoThirdHiddenTextLayoutMusic);
    }

    /**
     *
     * Da paso al siguiente crédito
     */
    private void nextCredit() {

        //El layout principal ya está seleccionado ponemos en marcha la animación del crédito actual
        logoTextLayoutCurrent.startAnimation(authorOutTransition);
        //Animación restaura el stepDot actual
        TransitionDrawable transitionCurrent = (TransitionDrawable) stepDotCurrent.getBackground();
        transitionCurrent.reverseTransition(getResources().getInteger(R.integer.tool_btn_color_transition));

        //Misma operación con el stepdot
        ScaleAnimation hideStepDot = new ScaleAnimation(1.5f, 1f, 1.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        hideStepDot.setDuration(getResources().getInteger(R.integer.tool_btn_scale));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideStepDot.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        hideStepDot.setFillAfter(true);

        stepDotCurrent.startAnimation(hideStepDot);

        //Animación de entrada del siguiente crédito
        indexCredits++;
        if (indexCredits == arrayCredits.size()) indexCredits = 0;
        logoTextLayoutNext = arrayCredits.get(indexCredits);

        logoTextLayoutNext.setVisibility(View.VISIBLE);

        logoTextLayoutNext.startAnimation(authorInTransition);
        //Animación selecciona el siguiente stepDot
        stepDotNext = arrayStepDots.get(indexCredits);
        TransitionDrawable transitionNext = (TransitionDrawable) stepDotNext.getBackground();
        transitionNext.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));

        ScaleAnimation showStepDot = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        showStepDot.setDuration(getResources().getInteger(R.integer.tool_btn_scale));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showStepDot.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        showStepDot.setFillAfter(true);

        stepDotNext.startAnimation(showStepDot);

        //Seleccionamos siguientes layouts de hidden info
        logoMainTextLayoutCurrent = arrayMainText.get(indexCredits);
        logoFirstHiddenTextLayoutCurrent = arrayFirstLine.get(indexCredits);
        logoSecondHiddenTextLayoutCurrent = arraySecondLine.get(indexCredits);
        logoThirdHiddenTextLayoutCurrent = arrayThirdLine.get(indexCredits);
    }

    /**
     *
     * Muestra el crédito anterior
     */
    private void previousCredit() {

        //El layout principal ya está seleccionado ponemos en marcha la animación del crédito actual
        logoTextLayoutCurrent.startAnimation(authorOutTransitionReverse);
        //Animación restaura el stepDot actual
        TransitionDrawable transitionCurrent = (TransitionDrawable) stepDotCurrent.getBackground();
        transitionCurrent.reverseTransition(getResources().getInteger(R.integer.tool_btn_color_transition));

        //Misma operación con el stepdot
        ScaleAnimation hideStepDot = new ScaleAnimation(1.5f, 1f, 1.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        hideStepDot.setDuration(getResources().getInteger(R.integer.tool_btn_scale));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideStepDot.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        hideStepDot.setFillAfter(true);

        stepDotCurrent.startAnimation(hideStepDot);

        //Animación de entrada del siguiente crédito
        indexCredits--;
        if (indexCredits < 0) indexCredits = arrayCredits.size() - 1;
        logoTextLayoutNext = arrayCredits.get(indexCredits);
        logoTextLayoutNext.setVisibility(View.VISIBLE);
        logoTextLayoutNext.startAnimation(authorInTransitionReverse);
        //Animación selecciona el siguiente stepDot
        stepDotNext = arrayStepDots.get(indexCredits);
        TransitionDrawable transitionNext = (TransitionDrawable) stepDotNext.getBackground();
        transitionNext.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));

        ScaleAnimation showStepDot = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        showStepDot.setDuration(getResources().getInteger(R.integer.tool_btn_scale));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showStepDot.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        showStepDot.setFillAfter(true);

        stepDotNext.startAnimation(showStepDot);

        //Seleccionamos siguientes layouts de hidden info
        logoMainTextLayoutCurrent = arrayMainText.get(indexCredits);
        logoFirstHiddenTextLayoutCurrent = arrayFirstLine.get(indexCredits);
        logoSecondHiddenTextLayoutCurrent = arraySecondLine.get(indexCredits);
        logoThirdHiddenTextLayoutCurrent = arrayThirdLine.get(indexCredits);
    }

    /**
     * Muestra la información oculta de los créditos
     */
    private void showHiddenInfo() {

        more_btn.startAnimation(hideMoreHiddenInfo);
        logoImage.startAnimation(translateLogoHiddenInfo);
        logoMainTextLayoutCurrent.startAnimation(translateMainTextHiddenInfo);
        logoFirstHiddenTextLayoutCurrent.startAnimation(showFirstLineHiddenInfo);
        logoSecondHiddenTextLayoutCurrent.startAnimation(showSecondLineHiddenInfo);
        logoThirdHiddenTextLayoutCurrent.startAnimation(showThirdLineHiddenInfo);

        programUnlock(showThirdLineHiddenInfo.getDuration()+showThirdLineHiddenInfo.getStartOffset());
    }

    /**
     * Oculta de nuevo la información oculta de los créditos
     */
    private void hideHiddenInfo() {

        less_btn.startAnimation(hideLess);
        logoImage.startAnimation(translateLogoHiddenInfoReverse);
        logoMainTextLayoutCurrent.startAnimation(translateMainTextHiddenInfoReverse);
        logoFirstHiddenTextLayoutCurrent.startAnimation(hideFirstLineHiddenInfo);
        logoSecondHiddenTextLayoutCurrent.startAnimation(hideSecondLineHiddenInfo);
        logoThirdHiddenTextLayoutCurrent.startAnimation(hideThirdLineHiddenInfo);

        //Programamos desbloqueo
        programUnlock(translateMainTextHiddenInfoReverse.getDuration()+translateMainTextHiddenInfoReverse.getStartOffset());

        //Volvemos a ocultar la info oculta de todos los créditos
        for (int i=0; i<arrayMainText.size(); i++) {

            LinearLayout layout = arrayMainText.get(i);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)layout.getLayoutParams();
            //params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.bottomMargin = (int) getResources().getDimension(R.dimen.margin_main_text_hidden_info_not_visible);
            layout.setLayoutParams(params);

            arrayFirstLine.get(i).setVisibility(View.INVISIBLE);
            arraySecondLine.get(i).setVisibility(View.INVISIBLE);
            arrayThirdLine.get(i).setVisibility(View.INVISIBLE);
        }
    }

    private void prepareAnimations() {

        prepareStandardAnimations();
        prepareHideShowLayoutAnimations();
        prepareArrowButtonsAnimations();
        prepareHiddenInfoAnimations();
    }

    /**
     * Prepara animaciones sin clasificar. Hearbeats, clicks...
     */
    private void prepareStandardAnimations(){

        //Cargamos la animación para el botón de música
        clickMusic = new AnimationSet(false);

        ScaleAnimation firstClickMusicScale = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        firstClickMusicScale.setDuration(getResources().getInteger(R.integer.scale_click));
        firstClickMusicScale.setRepeatMode(Animation.REVERSE);
        firstClickMusicScale.setRepeatCount(1);

        ScaleAnimation secondClickMusicScale = new ScaleAnimation(1f, 1f, 1f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        secondClickMusicScale.setDuration(getResources().getInteger(R.integer.wait_icon_animation));

        clickMusic.addAnimation(firstClickMusicScale);
        clickMusic.addAnimation(secondClickMusicScale);

        clickMusic.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "InfoActivity - clickMusic.onAnimationEnd");

                //Animación transformación voice on/off
                //On - > Off
                if (MusicManager.isMusic_on()) {

                    //Quitamos volumen de la música. Duplicado por si se interrumpe.
                    MusicManager.setMusicVolumeOff();

                    setMusicIconOff();
                    MusicManager.setMusic_on(false);
                }
                //Off - > On
                else {

                    //Restablecemos volumen de la música. Duplicado por si se interrumpe.
                    MusicManager.setMusicVolumeOn();

                    setMusicIconOn();
                    music_btn.startAnimation(heartBeatMusic);
                    MusicManager.setMusic_on(true);
                }
            }
        });

        //Cargamos animación al pulsar el botón de vuelta al menú principal
        clickBack = new ScaleAnimation(1f, 1.4f, 1f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickBack.setDuration(getResources().getInteger(R.integer.scale_click));
        clickBack.setRepeatMode(Animation.REVERSE);
        clickBack.setRepeatCount(1);

        //Cargamos animación de latido para el botón de música
        heartBeatMusic = new AnimationSet(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatMusic.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation heartBeatMusicScale = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatMusicScale.setRepeatMode(Animation.REVERSE);
        heartBeatMusicScale.setRepeatCount(Animation.INFINITE);
        heartBeatMusicScale.setDuration(getResources().getInteger(R.integer.heart_beat_music));

        RotateAnimation heartBeatMusicRotateFirst = new RotateAnimation(0, -15,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatMusicRotateFirst.setDuration(
                getResources().getInteger(R.integer.heart_beat_music_half));

        RotateAnimation heartBeatMusicRotateSecond = new RotateAnimation(0, 30,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatMusicRotateSecond.setDuration(getResources().getInteger(R.integer.heart_beat_music));
        heartBeatMusicRotateSecond.setStartOffset(
                getResources().getInteger(R.integer.heart_beat_music));
        heartBeatMusicRotateSecond.setRepeatMode(Animation.REVERSE);
        heartBeatMusicRotateSecond.setRepeatCount(Animation.INFINITE);

        heartBeatMusic.addAnimation(heartBeatMusicScale);
        heartBeatMusic.addAnimation(heartBeatMusicRotateFirst);
        heartBeatMusic.addAnimation(heartBeatMusicRotateSecond);

        //Cargamos animación de latido para el botón de back
        heartBeatBack = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatBack.setRepeatMode(Animation.REVERSE);
        heartBeatBack.setRepeatCount(Animation.INFINITE);
        heartBeatBack.setDuration(getResources().getInteger(R.integer.heart_beat_back));

        //Cargamos animación de latido para el botón de back
        heartBeatMore = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatMore.setRepeatMode(Animation.REVERSE);
        heartBeatMore.setRepeatCount(Animation.INFINITE);
        heartBeatMore.setDuration(getResources().getInteger(R.integer.heart_beat_arrow));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatMore.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de back
        heartBeatArrowLeft = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatArrowLeft.setRepeatMode(Animation.REVERSE);
        heartBeatArrowLeft.setRepeatCount(Animation.INFINITE);
        heartBeatArrowLeft.setDuration(getResources().getInteger(R.integer.heart_beat_arrow));

        //Cargamos animación de latido para el botón de back
        heartBeatArrowRight = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatArrowRight.setRepeatMode(Animation.REVERSE);
        heartBeatArrowRight.setRepeatCount(Animation.INFINITE);
        heartBeatArrowRight.setDuration(getResources().getInteger(R.integer.heart_beat_arrow));

        //Cargamos animación de latido para el botón de back
        float coefReduce = ((float) getResources().getDimension(R.dimen.diameter_divide_btn)) /
                ((float) getResources().getDimension(R.dimen.diameter_play_btn_big));

        heartBeatBackground = new ScaleAnimation(coefReduce, coefReduce * 1.1f,
                coefReduce, coefReduce * 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatBackground.setRepeatMode(Animation.REVERSE);
        heartBeatBackground.setRepeatCount(Animation.INFINITE);
        heartBeatBackground.setDuration(getResources().getInteger(R.integer.heart_beat_arrow));
    }

    /**
     * Prepara las animaciones para ocultar y mostrar el layout al cambiar a otra actividad
     */
    private void prepareHideShowLayoutAnimations() {

        //Cargamos la animación de aparición del botón de música
        showMusic = new TranslateAnimation(getResources().getDimension(R.dimen.translate_logo_btns),
                0, 0, 0);
        showMusic.setDuration(getResources().getInteger(R.integer.show_logo_btns));
        showMusic.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showMusic.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos la animación de aparición del botón de créditos
        showBack = new TranslateAnimation(-getResources().getDimension(R.dimen.translate_logo_btns),
                0, 0, 0);
        showBack.setDuration(getResources().getInteger(R.integer.show_logo_btns));
        showBack.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showBack.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos la animación de ocultación de botón de música
        hideMusic = new TranslateAnimation(0,
                getResources().getDimension(R.dimen.translate_logo_btns), 0, 0);
        hideMusic.setDuration(getResources().getInteger(R.integer.hide_logo_btns));
        hideMusic.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideMusic.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        hideMusic.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideMusic.onAnimationStart");

                music_btn.setClickable(false);
                music_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideMusic.onAnimationEnd");
            }
        });

        //Cargamos la animación de ocultación de botón de back
        hideBack = new TranslateAnimation(0,
                -getResources().getDimension(R.dimen.translate_logo_btns), 0, 0);
        hideBack.setDuration(getResources().getInteger(R.integer.hide_logo_btns));
        hideBack.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideBack.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        hideBack.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideBack.onAnimationStart");

                back_btn.setClickable(false);
                back_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideBack.onAnimationEnd");
            }
        });

        //Preparamos animaciones de la transición inicial
        colorBarOutTransition = new TranslateAnimation( 0, 0, 0,
                getResources().getDimension(R.dimen.translate_color_bar));
        colorBarOutTransition.setDuration(getResources().getInteger(R.integer.translate_color_bar));
        colorBarOutTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            colorBarOutTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        colorBarOutTransition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - colorBarOutTransition.onAnimationEnd");

                //Finalizamos la actividad
                InfoActivity.this.finish();
                overridePendingTransition(0, 0);

                continue_music = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoTextOutTransition = new TranslateAnimation(
                0, getResources().getDimension(R.dimen.translate_logos), 0, 0);
        logoTextOutTransition.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        logoTextOutTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoTextOutTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        logoSubtextOutTransition = new TranslateAnimation(
                0, getResources().getDimension(R.dimen.translate_logos), 0, 0);
        logoSubtextOutTransition.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        logoSubtextOutTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoSubtextOutTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        logoImageOutTransition = new TranslateAnimation(
                0, -getResources().getDimension(R.dimen.translate_logos), 0, 0);
        logoImageOutTransition.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        logoImageOutTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoImageOutTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        authorOutTransition = new TranslateAnimation(
                0, getResources().getDimension(R.dimen.translate_logos), 0, 0);
        authorOutTransition.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        authorOutTransition.setFillAfter(true);

        authorOutTransition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - authorOutTransition.onAnimationEnd");

                //Desligamos la animación y seleccionamos el nuevo crédito y stepdot actuales
                logoTextLayoutCurrent.setVisibility(View.INVISIBLE);
                logoTextLayoutCurrent.clearAnimation();
                logoTextLayoutCurrent = arrayCredits.get(indexCredits);
                stepDotCurrent = arrayStepDots.get(indexCredits);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        authorOutTransitionReverse = new TranslateAnimation(
                0, -getResources().getDimension(R.dimen.translate_logos), 0, 0);
        authorOutTransitionReverse.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        authorOutTransitionReverse.setFillAfter(true);

        authorOutTransitionReverse.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - authorOutTransitionReverse.onAnimationEnd");

                //Desligamos la animación y seleccionamos el nuevo crédito y stepdot actuales
                logoTextLayoutCurrent.setVisibility(View.INVISIBLE);
                logoTextLayoutCurrent.clearAnimation();
                logoTextLayoutCurrent = arrayCredits.get(indexCredits);
                stepDotCurrent = arrayStepDots.get(indexCredits);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        stepDotsOutTransition = new TranslateAnimation(
                0, getResources().getDimension(R.dimen.translate_logos), 0, 0);
        stepDotsOutTransition.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        stepDotsOutTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            stepDotsOutTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        //Preparamos las animaciones de la transición final
        colorBarInTransition = new TranslateAnimation( 0, 0,
                getResources().getDimension(R.dimen.translate_color_bar), 0);
        colorBarInTransition.setDuration(getResources().getInteger(R.integer.translate_color_bar));
        colorBarInTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            colorBarInTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        colorBarInTransition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - colorBarInTransition.onAnimationEnd");

                startPlayToArrows();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoTextInTransition = new TranslateAnimation(
                getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        logoTextInTransition.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        logoTextInTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoTextInTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        logoSubtextInTranstion = new TranslateAnimation(
                getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        logoSubtextInTranstion.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        logoSubtextInTranstion.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoSubtextInTranstion.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        logoImageInTransition = new TranslateAnimation(
                -getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        logoImageInTransition.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        logoImageInTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoImageInTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        authorInTransition = new TranslateAnimation(
                -getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        authorInTransition.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        authorInTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            authorInTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        authorInTransition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - authorInTransition.onAnimationEnd");

                //Desligamos la animación del crédito.
                logoTextLayoutNext.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        authorInTransitionReverse = new TranslateAnimation(
                getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        authorInTransitionReverse.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        authorInTransitionReverse.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            authorInTransitionReverse.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        authorInTransitionReverse.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - authorInTransitionReverse.onAnimationEnd");

                //Desligamos la animación del crédito.
                logoTextLayoutNext.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        stepDotsInTransition = new TranslateAnimation(
                getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        stepDotsInTransition.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        stepDotsInTransition.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            stepDotsInTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        logoSubtextInTranstion.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoTextInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoImageInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        stepDotsInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        showMusic.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
        showBack.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
    }

    /**
     * Prepara la animación de transformación del botón de play a botones more
     */
    private void prepareArrowButtonsAnimations(){

        hidePlay = new AnimationSet(false);

        RotateAnimation rotateHidePlay = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rotateHidePlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        rotateHidePlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        ScaleAnimation scaleHidePlay = new ScaleAnimation(1f, 0, 1f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHidePlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHidePlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        hidePlay.addAnimation(rotateHidePlay);
        hidePlay.addAnimation(scaleHidePlay);

        hidePlay.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hidePlay.onAnimationEnd");

                play_btn.setClickable(false);
                play_btn.setVisibility(View.INVISIBLE);
                more_btn.startAnimation(showMore);
            }
        });

        showPlay = new AnimationSet(true);

        ScaleAnimation scaleShowPlay = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowPlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        RotateAnimation rotateShowPlay = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateShowPlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        showPlay.addAnimation(scaleShowPlay);
        showPlay.addAnimation(rotateShowPlay);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showPlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showPlay.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showPlay.onAnimationStart");

                play_btn.setVisibility(View.VISIBLE);
                play_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showPlay.onAnimationEnd");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    startHideLayout();
                }
                else {
                    startHideLayoutBL();
                }
            }
        });

        hideMore = new AnimationSet(false);

        ScaleAnimation scaleHideMore = new ScaleAnimation(1f, 0, 1f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideMore.setDuration(getResources().getInteger(R.integer.change_icon_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideMore.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        RotateAnimation rotateMorePlay = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rotateMorePlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        rotateMorePlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        hideMore.addAnimation(rotateMorePlay);
        hideMore.addAnimation(scaleHideMore);

        hideMore.setStartOffset(getResources().getInteger(R.integer.delay_hide_more));

        hideMore.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideMore.onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideMore.onAnimationEnd");

                more_btn.setClickable(false);
                more_btn.setVisibility(View.INVISIBLE);
                play_btn.startAnimation(showPlay);
            }
        });

        showMore = new AnimationSet(true);

        ScaleAnimation scaleShowMore = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowMore.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        RotateAnimation rotateShowMore = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateShowMore.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        showMore.addAnimation(scaleShowMore);
        showMore.addAnimation(rotateShowMore);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showMore.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showMore.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showMore.onAnimationStart");

                more_btn.setVisibility(View.VISIBLE);
                more_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showMore.onAnimationEnd");

            }
        });

        //Preparamos animación de mostrar el botón de rate
        showArrowLeft = new AnimationSet(true);

        //Esto lo incluimos para que no sea visible antes de tiempo
        AlphaAnimation alphaShowArrowLeft = new AlphaAnimation(0, 1);
        alphaShowArrowLeft.setDuration(getResources().getInteger(R.integer.alpha_show_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaShowArrowLeft.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleShowArrowLeft = new ScaleAnimation(0.4f, 1, 0.4f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowArrowLeft.setDuration(getResources().getInteger(R.integer.show_arrow_btns));

        TranslateAnimation translateShowArrowLeft = new TranslateAnimation(
                getResources().getDimension(R.dimen.margin_horizontal_arrow_btn), 0, 0, 0);
        translateShowArrowLeft.setDuration(getResources().getInteger(R.integer.show_arrow_btns));

        showArrowLeft.addAnimation(alphaShowArrowLeft);
        showArrowLeft.addAnimation(scaleShowArrowLeft);
        showArrowLeft.addAnimation(translateShowArrowLeft);

        showArrowLeft.setStartOffset(getResources().getInteger(R.integer.delay_show_arrow_btns));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showArrowLeft.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showArrowLeft.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showArrowLeft.onAnimationStart");

                arrow_left_btn.setVisibility(View.VISIBLE);
                arrow_left_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showArrowLeft.onAnimationEnd");

                //Animaciones heartbeat
                if (MusicManager.isMusic_on())music_btn.startAnimation(heartBeatMusic);
                back_btn.startAnimation(heartBeatBack);
                more_btn.startAnimation(heartBeatMore);
                arrow_left_btn.startAnimation(heartBeatArrowLeft);
                arrow_right_btn.startAnimation(heartBeatArrowRight);
                background_btn.startAnimation(heartBeatBackground);
            }
        });

        //Preparamos animación de ocultar el botón de rate
        hideArrowLeft = new AnimationSet(true);

        ScaleAnimation scaleArrowLeftHide = new ScaleAnimation(1, 0.4f, 1, 0.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleArrowLeftHide.setDuration(getResources().getInteger(R.integer.hide_arrow_btns));

        TranslateAnimation translateArrowLeftHide = new TranslateAnimation(0,
                getResources().getDimension(R.dimen.margin_horizontal_arrow_btn), 0, 0);
        translateArrowLeftHide.setDuration(getResources().getInteger(R.integer.hide_arrow_btns));
        translateArrowLeftHide.setFillAfter(true);

        hideArrowLeft.addAnimation(scaleArrowLeftHide);
        hideArrowLeft.addAnimation(translateArrowLeftHide);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideArrowLeft.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideArrowLeft.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideArrowLeft.onAnimationEnd");

                arrow_left_btn.setClickable(false);
                arrow_left_btn.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar el botón de ArrowRight
        showArrowRight = new AnimationSet(true);

        //Esto lo incluimos para que no sea visible antes de tiempo
        AlphaAnimation alphaShowArrowRight = new AlphaAnimation(0, 1);
        alphaShowArrowRight.setDuration(getResources().getInteger(R.integer.alpha_show_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaShowArrowRight.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleArrowRightShow = new ScaleAnimation(0.4f, 1, 0.4f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleArrowRightShow.setDuration(getResources().getInteger(R.integer.show_arrow_btns));

        TranslateAnimation translateArrowRightShow = new TranslateAnimation(
                -getResources().getDimension(R.dimen.margin_horizontal_arrow_btn), 0, 0, 0);
        translateArrowRightShow.setDuration(getResources().getInteger(R.integer.show_arrow_btns));
        translateArrowRightShow.setFillAfter(true);

        showArrowRight.addAnimation(alphaShowArrowRight);
        showArrowRight.addAnimation(scaleArrowRightShow);
        showArrowRight.addAnimation(translateArrowRightShow);

        showArrowRight.setStartOffset(getResources().getInteger(R.integer.delay_show_arrow_btns));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showArrowRight.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showArrowRight.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showArrowRight.onAnimationStart");

                arrow_right_btn.setVisibility(View.VISIBLE);
                arrow_right_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showArrowRight.onAnimationEnd");
            }
        });

        //Preparamos animación de ocultar el botón de ArrowRight
        hideArrowRight = new AnimationSet(true);

        ScaleAnimation scaleHideArrowRight = new ScaleAnimation(1, 0.4f, 1, 0.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideArrowRight.setDuration(getResources().getInteger(R.integer.hide_arrow_btns));

        TranslateAnimation translateHideArrowRight = new TranslateAnimation(0,
                -getResources().getDimension(R.dimen.margin_horizontal_arrow_btn), 0, 0);
        translateHideArrowRight.setDuration(getResources().getInteger(R.integer.hide_arrow_btns));
        translateHideArrowRight.setFillAfter(true);

        hideArrowRight.addAnimation(scaleHideArrowRight);
        hideArrowRight.addAnimation(translateHideArrowRight);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideArrowRight.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideArrowRight.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideArrowRight.onAnimationEnd");

                arrow_right_btn.setClickable(false);
                arrow_right_btn.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animaciones para ajustar el tamaño del botón principal al transformar el icono
        float coefReduce = ((float) getResources().getDimension(R.dimen.diameter_divide_btn)) /
                ((float) getResources().getDimension(R.dimen.diameter_play_btn_big));

        reduceBackgroundButton = new ScaleAnimation(1,coefReduce, 1, coefReduce,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        reduceBackgroundButton.setDuration(getResources().getInteger(R.integer.reduce_background_btn_arrow));
        reduceBackgroundButton.setFillAfter(true);
        reduceBackgroundButton.setStartOffset(getResources().getInteger(R.integer.delay_reduce_background_arrow));

        augmentBackgroundButton = new ScaleAnimation(coefReduce, 1, coefReduce, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        augmentBackgroundButton.setDuration(getResources().getInteger(R.integer.augment_background_btn_arrow));
        augmentBackgroundButton.setFillAfter(true);
        augmentBackgroundButton.setStartOffset(getResources().getInteger(R.integer.delay_augment_background_arrow));
    }

    /**
     * Prepara las animaciones de la información oculta de los créditos
     */
    private void prepareHiddenInfoAnimations(){

        //Animación para reducir el logo
        reduceLogoHiddenInfo = new AnimationSet(true);

        ScaleAnimation reduceLogoScale = new ScaleAnimation(1, 0.5f, 1, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        reduceLogoScale.setDuration(getResources().getInteger(R.integer.reduce_logo_hidden_info));

        TranslateAnimation translateLogo = new TranslateAnimation(0, 0, 0,
                -getResources().getDimension(R.dimen.translate_logo_hidden_info));
        translateLogo.setDuration(getResources().getInteger(R.integer.translate_logo_hidden_info));

        reduceLogoHiddenInfo.addAnimation(reduceLogoScale);
        reduceLogoHiddenInfo.addAnimation(translateLogo);

        reduceLogoHiddenInfo.setFillAfter(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            reduceLogoHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Animación para aumentar el logo
        augmentLogoHiddenInfo = new AnimationSet(true);

        ScaleAnimation augmentLogoScale = new ScaleAnimation(0.5f, 1, 0.5f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        augmentLogoScale.setDuration(getResources().getInteger(R.integer.reduce_logo_hidden_info));

        TranslateAnimation translateLogoReverse = new TranslateAnimation(0, 0,
                -getResources().getDimension(R.dimen.translate_logo_hidden_info), 0);
        translateLogoReverse.setDuration(getResources().getInteger(R.integer.translate_logo_hidden_info));

        augmentLogoHiddenInfo.addAnimation(augmentLogoScale);
        augmentLogoHiddenInfo.addAnimation(translateLogoReverse);

        augmentLogoHiddenInfo.setFillAfter(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            augmentLogoHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        augmentLogoHiddenInfo.setStartOffset(getResources().getInteger(R.integer.delay_third_line_hidden_info_return));

        //Animación esconder el logo
        translateLogoHiddenInfo = new TranslateAnimation(0, 0, 0,
                -getResources().getDimension(R.dimen.translate_logo_hidden_info_to_hide));
        translateLogoHiddenInfo.setDuration(
                getResources().getInteger(R.integer.translate_logo_hidden_info));
        translateLogoHiddenInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateLogoHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Animación esconder el logo
        translateLogoHiddenInfoReverse = new TranslateAnimation(0, 0,
                -getResources().getDimension(R.dimen.translate_logo_hidden_info_to_hide), 0);
        translateLogoHiddenInfoReverse.setDuration(
                getResources().getInteger(R.integer.translate_logo_hidden_info));
        translateLogoHiddenInfoReverse.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateLogoHiddenInfoReverse.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        translateLogoHiddenInfoReverse.setStartOffset(
                getResources().getInteger(R.integer.delay_logo_hidden_info_return) );

        //Animación para mover el texto principal del crédito
        translateMainTextHiddenInfo = new TranslateAnimation(0, 0, 0,
                -getResources().getDimension(R.dimen.translate_main_text_hidden_info));
        translateMainTextHiddenInfo.setDuration(
                getResources().getInteger(R.integer.translate_main_text_hidden_info));
        translateMainTextHiddenInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateMainTextHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        translateMainTextHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.METHOD, "InfoActivity - translateMainTextHiddenInfo.onAnimationEnd");

                //Fix absurdo. Evita flick en el botón al cambiar su posición con setLayoutParams dentro de onAnimationEnd
				/*
				animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
	            animation.setDuration(1);
	            play_btn.startAnimation(animation);
	            */

                //Mostramos la info oculta de todos los créditos
                for (int i=0; i<arrayMainText.size(); i++) {

                    LinearLayout layout = arrayMainText.get(i);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)layout.getLayoutParams();
                    params.bottomMargin = (int) getResources().getDimension(R.dimen.margin_main_text_hidden_info_visible);
                    layout.setLayoutParams(params);

                    arrayFirstLine.get(i).setVisibility(View.VISIBLE);
                    arraySecondLine.get(i).setVisibility(View.VISIBLE);
                    arrayThirdLine.get(i).setVisibility(View.VISIBLE);
                }

                logoMainTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Animación para devolver el texto principal del crédito a su posición original
        translateMainTextHiddenInfoReverse = new TranslateAnimation(0, 0,
                -getResources().getDimension(R.dimen.translate_main_text_hidden_info), 0);
        translateMainTextHiddenInfoReverse.setDuration(
                getResources().getInteger(R.integer.translate_main_text_hidden_info));
        translateMainTextHiddenInfoReverse.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateMainTextHiddenInfoReverse.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        translateMainTextHiddenInfoReverse.setStartOffset(getResources().getInteger(R.integer.delay_third_line_hidden_info_return));
        translateMainTextHiddenInfoReverse.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.METHOD, "InfoActivity - translateMainTextHiddenInfoReverse.onAnimationEnd");

                logoMainTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //Animación texto web
        showFirstLineHiddenInfo = new AnimationSet(true);

        AlphaAnimation alphaWeb = new AlphaAnimation(0,1);
        alphaWeb.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        TranslateAnimation translateWeb = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info), 0);
        translateWeb.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        showFirstLineHiddenInfo.setStartOffset(
                getResources().getInteger(R.integer.delay_first_line_hidden_info));
        showFirstLineHiddenInfo.addAnimation(alphaWeb);
        showFirstLineHiddenInfo.addAnimation(translateWeb);

        showFirstLineHiddenInfo.setFillAfter(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showFirstLineHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showFirstLineHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showFirstLineHiddenInfo.onAnimationStart");

                logoFirstHiddenTextLayoutCurrent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showFirstLineHiddenInfo.onAnimationEnd");
                logoFirstHiddenTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideFirstLineHiddenInfo = new AnimationSet(true);

        AlphaAnimation alphaWebHide = new AlphaAnimation(1,0);
        alphaWebHide.setDuration(getResources().getInteger(R.integer.alpha_return_hidden_info_3));

        TranslateAnimation translateWebHide = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info));
        translateWebHide.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        hideFirstLineHiddenInfo.addAnimation(alphaWebHide);
        hideFirstLineHiddenInfo.addAnimation(translateWebHide);

        hideFirstLineHiddenInfo.setFillAfter(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideFirstLineHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        hideFirstLineHiddenInfo.setStartOffset(getResources().getInteger(R.integer.delay_second_line_hidden_info_return));

        hideFirstLineHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideFirstLineHiddenInfo.onAnimationStart");

                logoFirstHiddenTextLayoutCurrent.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideFirstLineHiddenInfo.onAnimationEnd");

                logoFirstHiddenTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Animación texto mail
        showSecondLineHiddenInfo = new AnimationSet(true);

        AlphaAnimation alphaMail = new AlphaAnimation(0,1);
        alphaMail.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        TranslateAnimation translateMail = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info), 0);
        translateMail.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        showSecondLineHiddenInfo.setStartOffset(
                getResources().getInteger(R.integer.delay_second_line_hidden_info));
        showSecondLineHiddenInfo.addAnimation(alphaMail);
        showSecondLineHiddenInfo.addAnimation(translateMail);

        showSecondLineHiddenInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showSecondLineHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showSecondLineHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showSecondLineHiddenInfo.onAnimationEnd");

                logoSecondHiddenTextLayoutCurrent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showSecondLineHiddenInfo.onAnimationEnd");

                logoSecondHiddenTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideSecondLineHiddenInfo = new AnimationSet(true);

        AlphaAnimation alphaMailHide = new AlphaAnimation(1,0);
        alphaMailHide.setDuration(getResources().getInteger(R.integer.alpha_return_hidden_info_2));

        TranslateAnimation translateMailHide = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info));
        translateMailHide.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        hideSecondLineHiddenInfo.addAnimation(alphaMailHide);
        hideSecondLineHiddenInfo.addAnimation(translateMailHide);

        hideSecondLineHiddenInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideSecondLineHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        hideSecondLineHiddenInfo.setStartOffset(getResources().getInteger(R.integer.delay_first_line_hidden_info_return));

        hideSecondLineHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideSecondLineHiddenInfo.onAnimationStart");

                logoSecondHiddenTextLayoutCurrent.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideSecondLineHiddenInfo.onAnimationEnd");

                logoSecondHiddenTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Animación texto tlf
        showThirdLineHiddenInfo = new AnimationSet(true);

        AlphaAnimation alphaTlf = new AlphaAnimation(0,1);
        alphaTlf.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        TranslateAnimation translateTlf = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info), 0);
        translateTlf.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        showThirdLineHiddenInfo.setStartOffset(
                getResources().getInteger(R.integer.delay_third_line_hidden_info));
        showThirdLineHiddenInfo.addAnimation(alphaTlf);
        showThirdLineHiddenInfo.addAnimation(translateTlf);

        showThirdLineHiddenInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showThirdLineHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showThirdLineHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showThirdLineHiddenInfo.onAnimationStart");

                logoThirdHiddenTextLayoutCurrent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showThirdLineHiddenInfo.onAnimationEnd");

                logoThirdHiddenTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideThirdLineHiddenInfo = new AnimationSet(true);

        AlphaAnimation alphaTlfHide = new AlphaAnimation(1,0);
        alphaTlfHide.setDuration(getResources().getInteger(R.integer.alpha_return_hidden_info));

        TranslateAnimation translateTlfHide = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info));
        translateTlfHide.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        hideThirdLineHiddenInfo.addAnimation(alphaTlfHide);
        hideThirdLineHiddenInfo.addAnimation(translateTlfHide);

        hideThirdLineHiddenInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideThirdLineHiddenInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideThirdLineHiddenInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideThirdLineHiddenInfo.onAnimationStart");

                logoThirdHiddenTextLayoutCurrent.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideThirdLineHiddenInfo.onAnimationEnd");

                logoThirdHiddenTextLayoutCurrent.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideThirdLineHiddenInfoForExit = new AnimationSet(true);

        AlphaAnimation alphaTlfHideForExit = new AlphaAnimation(1,0);
        alphaTlfHideForExit.setDuration(getResources().getInteger(R.integer.alpha_return_hidden_info));

        TranslateAnimation translateTlfHideForExit = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.translate_hidden_info));
        translateTlfHideForExit.setDuration(getResources().getInteger(R.integer.translate_main_text_hidden_info));

        hideThirdLineHiddenInfoForExit.addAnimation(alphaTlfHideForExit);
        hideThirdLineHiddenInfoForExit.addAnimation(translateTlfHideForExit);

        hideThirdLineHiddenInfoForExit.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideThirdLineHiddenInfoForExit.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideThirdLineHiddenInfoForExit.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideThirdLineHiddenInfoForExit.onAnimationStart");

                logoThirdHiddenTextLayoutCurrent.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideThirdLineHiddenInfoForExit.onAnimationEnd");

                logoThirdHiddenTextLayoutCurrent.clearAnimation();

                startArrowsToPlay();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideMoreHiddenInfo = new AnimationSet(false);

        ScaleAnimation scaleHideMore = new ScaleAnimation(1f, 0, 1f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideMore.setDuration(getResources().getInteger(R.integer.change_icon_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideMore.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        RotateAnimation rotateMorePlay = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rotateMorePlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        rotateMorePlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        hideMoreHiddenInfo.addAnimation(rotateMorePlay);
        hideMoreHiddenInfo.addAnimation(scaleHideMore);

        hideMoreHiddenInfo.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideMoreHiddenInfo.onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideMoreHiddenInfo.onAnimationEnd");

                more_btn.setClickable(false);
                more_btn.setVisibility(View.INVISIBLE);
                less_btn.startAnimation(showLess);
            }
        });

        //Reutilizamos el showMore de las animaciones arrowButtons

        hideLess = new AnimationSet(false);

        ScaleAnimation scaleHideLess = new ScaleAnimation(1f, 0, 1f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideLess.setDuration(getResources().getInteger(R.integer.change_icon_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideLess.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        RotateAnimation rotateLessPlay = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rotateLessPlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        rotateLessPlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        hideLess.addAnimation(rotateLessPlay);
        hideLess.addAnimation(scaleHideLess);

        hideLess.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideLess.onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - hideLess.onAnimationEnd");

                less_btn.setClickable(false);
                less_btn.setVisibility(View.INVISIBLE);
                more_btn.startAnimation(showMore);
            }
        });

        showLess = new AnimationSet(true);

        ScaleAnimation scaleShowLess = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowLess.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        RotateAnimation rotateShowLess = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateShowLess.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        showLess.addAnimation(scaleShowLess);
        showLess.addAnimation(rotateShowLess);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showLess.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showLess.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showLess.onAnimationStart");

                less_btn.setVisibility(View.VISIBLE);
                less_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "InfoActivity - showLess.onAnimationEnd");

            }
        });

    }

    /**
     * Muestra las vistas de la interfaz
     */
    private void startShowLayout() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startShowLayout");

        lockButtons();

        //colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayoutCurrent.startAnimation(logoTextInTransition);
        //logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransition);
        stepDotsLayout.startAnimation(stepDotsInTransition);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        back_btn.startAnimation(showBack);
        back_btn.setVisibility(View.VISIBLE);
        back_btn.setClickable(true);

        //Transición del stepDot
        TransitionDrawable t = (TransitionDrawable) stepDotCurrent.getBackground();
        t.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));

        ScaleAnimation showStepDot = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        showStepDot.setDuration(getResources().getInteger(R.integer.tool_btn_scale));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showStepDot.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        showStepDot.setFillAfter(true);

        stepDotCurrent.startAnimation(showStepDot);

        startUnvealInfo();
    }

    /**
     * Muestra las vistas de la interfaz
     */
    private void startShowLayoutBL() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startShowLayoutBL");

        lockButtons();

        colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayoutCurrent.startAnimation(logoTextInTransition);
        //logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransition);
        stepDotsLayout.startAnimation(stepDotsInTransition);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        back_btn.startAnimation(showBack);
        back_btn.setVisibility(View.VISIBLE);
        back_btn.setClickable(true);

        //Transición del stepDot
        TransitionDrawable t = (TransitionDrawable) stepDotCurrent.getBackground();
        t.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));

        ScaleAnimation showStepDot = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        showStepDot.setDuration(getResources().getInteger(R.integer.tool_btn_scale));

        stepDotCurrent.startAnimation(showStepDot);
    }

    /**
     * Oculta las vistas de la interfaz
     */
    private void startHideLayout() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startHideLayout");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark, null));
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark));
        }

        //colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayoutCurrent.startAnimation(logoTextOutTransition);
        //logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);

        stepDotsLayout.startAnimation(stepDotsOutTransition);
        music_btn.startAnimation(hideMusic);
        back_btn.startAnimation(hideBack);
        startRevealInfoReturn();
    }

    /**
     * Oculta las vistas de la interfaz
     */
    private void startHideLayoutBL() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startHideLayoutBL");

        colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayoutCurrent.startAnimation(logoTextOutTransition);
        //logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);

        stepDotsLayout.startAnimation(stepDotsOutTransition);
        music_btn.startAnimation(hideMusic);
        back_btn.startAnimation(hideBack);
    }


    private void startPlayToArrows() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startPlayToArrows");

        //Mostramos los botones de arrow
        play_btn.startAnimation(hidePlay);
        //Con delays
        arrow_left_btn.startAnimation(showArrowLeft);
        arrow_right_btn.startAnimation(showArrowRight);
        background_btn.startAnimation(reduceBackgroundButton);

        //Programamos desbloqueo
        programUnlock(showArrowLeft.getDuration()+showArrowLeft.getStartOffset());
    }

    private void startArrowsToPlay() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startArrowsToPlay");

        //Escondemos los botones de arrow
        more_btn.startAnimation(hideMore);
        background_btn.startAnimation(augmentBackgroundButton);
        arrow_left_btn.startAnimation(hideArrowLeft);
        arrow_right_btn.startAnimation(hideArrowRight);
    }

    private void exitAnimations() {

        //Si está visible hidden info escondemos primero
        if (is_hidden_info_visible) {

            back_btn.startAnimation(clickBack);
            less_btn.startAnimation(hideLess);
            logoImage.startAnimation(translateLogoHiddenInfoReverse);
            logoMainTextLayoutCurrent.startAnimation(translateMainTextHiddenInfoReverse);
            logoFirstHiddenTextLayoutCurrent.startAnimation(hideFirstLineHiddenInfo);
            logoSecondHiddenTextLayoutCurrent.startAnimation(hideSecondLineHiddenInfo);
            logoThirdHiddenTextLayoutCurrent.startAnimation(hideThirdLineHiddenInfoForExit);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)logoMainTextLayoutCurrent.getLayoutParams();
            //params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.bottomMargin = (int) getResources().getDimension(R.dimen.margin_main_text_hidden_info_not_visible);
            logoMainTextLayoutCurrent.setLayoutParams(params);
        }
        else {
            startArrowsToPlay();
        }
    }

    /**
     * Pone en marcha animación Reveal Effect en reverso. Utilizada al entrar en la actividad,
     * después de la primera parte realizada en InfoActivity.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUnvealInfo() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startUnvealInfo");

        mainLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right,
                                       int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                v.removeOnLayoutChangeListener(this);

                //Calculamos el centro de la animación
                int cx = (play_btn.getLeft() + play_btn.getRight()) / 2;
                int cy = (play_btn.getTop() + play_btn.getBottom()) / 2;

                //Calculamos el radio de la animación
                int initialRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                        Math.pow(revealLayout.getHeight(), 2));

                //Creamos la animación
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(revealLayout,
                                cx, cy, initialRadius,
                                getResources().getDimension(R.dimen.diameter_logo_btns) / 2);
                anim.setDuration(getResources().getInteger(R.integer.unveal_long));

                //Hacemos visible la vista y empezamos la animación
                anim.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.v(Constants.Log.METHOD, "InfoActivity - startUnvealInfo.onAnimationStart");

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.v(Constants.Log.METHOD, "InfoActivity - startUnvealInfo.onAnimationEnd");

                        revealLayout.setVisibility(View.INVISIBLE);

                        startPlayToArrows();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }
                });
                //anim.setStartDelay(getResources().getInteger(R.integer.delay_reveal_info));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                            getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
                }
                anim.start();
            }
        });
    }

    /**
     * Pone en marcha animación Reveal Effect al pulsar el botón de cerrar.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startRevealInfoReturn() {

        Log.v(Constants.Log.METHOD, "InfoActivity - startRevealInfoReturn");

        //Calculamos el centro de la animación
        int cx = (play_btn.getLeft() + play_btn.getRight()) / 2;
        int cy = (play_btn.getTop() + play_btn.getBottom()) / 2;

        //Calculamos el radio de la animación
        int finalRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, 0, finalRadius);
        anim.setDuration(getResources().getInteger(R.integer.reveal_standard_return));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "InfoActivity - startRevealInfoReturn.onAnimationStart");

                //Hacemos visible el reveal y le cambiamos el color
                revealLayout.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.color_primary, null));
                } else {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.color_primary));
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "InfoActivity - startRevealInfoReturn.onAnimationEnd");

                //Finalizamos la actividad
                InfoActivity.this.finish();
                overridePendingTransition(0, 0);

                continue_music = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        anim.setStartDelay(getResources().getInteger(R.integer.delay_reveal_standard_return));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        anim.start();
    }

    /**
     * Pone el icono de música de la barra de controles según densidad de pixel.
     */
    private void setMusicIconOn() {

        Log.v(Constants.Log.METHOD, "InfoActivity - setMusicIconOn");

        final int mask = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if (mask == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            music_btn.setImageResource(R.drawable.ic_music_note_white_18dp);
        }
        else  if (mask == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_white_24dp);
        }
        else  if (mask == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_white_24dp);
        }
        else {
            music_btn.setImageResource(R.drawable.ic_music_note_white_18dp);
        }
    }

    /**
     * Pone el icono de música de la barra de controles según densidad de pixel.
     */
    private void setMusicIconOff() {

        Log.v(Constants.Log.METHOD, "InfoActivity - setMusicIconOff");

        final int mask = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (mask == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_18dp);

        }
        else  if (mask == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_24dp);
        }
        else  if (mask == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_24dp);
        }
        else {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_18dp);
        }
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {

        Log.w(Constants.Log.METHOD, "InfoActivity - hideSystemUI");

        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * Escondemos las barras de status y nav al coger foco
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {

            boolean visible = (mDecorView.getSystemUiVisibility()
                    & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

            if (visible) {

                hideSystemUI();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    /**
     * Controlamos con el singletap esconder las barras de status y nav
     * @author quayo
     *
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            boolean visible = (mDecorView.getSystemUiVisibility()
                    & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

            if (visible) {

                hideSystemUI();
            }

            return super.onSingleTapConfirmed(e);
        }
    }

    /**
     * Bloquea el botón de play
     */
    public void lockButtons() {

        btn_locked = true;

        Log.i(Constants.Log.METHOD, "InfoActivity - lockButtons = "+btn_locked);
    }

    /**
     * Desbloquea el botón de play
     */
    public void unlockButtons() {

        btn_locked = false;

        Log.i(Constants.Log.METHOD, "InfoActivity - unlockButtons = "+btn_locked);
    }

    /**
     * Programa desbloqueo dentro de "duration" ms.
     * @param duration
     */
    protected void programUnlock(long duration) {

        Log.i(Constants.Log.METHOD, "InfoActivity - programUnlock: "+duration);

        //Programamos desbloqueo
        new Handler().postDelayed(new Runnable() {
            public void run() {

                unlockButtons();
            }
        }, duration);
    }

    /**
     * Comprueba si el botón está bloqueado o no
     * @return
     */
    public boolean areButtonsLocked() {

        Log.v(Constants.Log.METHOD, "InfoActivity - Locked = " + btn_locked);

        return btn_locked;
    }

    @Override
    protected void onResume() {
        Log.v(Constants.Log.METHOD, "InfoActivity - onResume");

        //Escondemos barras
        hideSystemUI();

        MusicManager.startResumeMusic();

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(Constants.Log.METHOD, "InfoActivity - onPause");

        //Pausamos música
        if (!continue_music) MusicManager.pauseMusic();

        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.v(Constants.Log.METHOD, "InfoActivity - onRestart");

        MusicManager.createMusicRestart(true);

        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.v(Constants.Log.METHOD, "InfoActivity - onStop");

        if (!continue_music) MusicManager.stopMusic();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(Constants.Log.METHOD, "InfoActivity - onDestroy");

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        Log.v(Constants.Log.METHOD, "InfoActivity - onBackPressed");

        if (!areButtonsLocked()) {

            lockButtons();
            back_btn.startAnimation(clickBack);
            exitAnimations();
        }
    }
}
