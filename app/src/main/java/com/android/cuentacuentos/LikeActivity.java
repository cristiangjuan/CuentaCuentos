package com.android.cuentacuentos;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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
import android.view.Gravity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import utils.Constants;
import utils.MusicManager;

public class LikeActivity extends AppCompatActivity {


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
     * Botón de compartir en créditos
     */
    private ImageButton share_btn;
    /**
     * Botón de compartir en créditos
     */
    private ImageButton rate_btn;
    /**
     * Botón de compartir en créditos
     */
    private ImageButton donate_btn;
    /**
     * Botón de background
     */
    private ImageButton background_btn;
    /**
     * Texto principal del logo
     */
    private TextView logoTextView;
    /**
     * Cuadro de texto secundario
     */
    private TextView logoSubTextView;
    /**
     * Cuadro de texto secundario
     */
    private TextView shareTextView;
    /**
     * Cuadro de texto secundario
     */
    private TextView rateTextView;
    /**
     * Cuadro de texto secundario
     */
    private TextView donateTextView;
    /**
     * Imagen del logo
     */
    private ImageView logoImage;
    /*
     ******************* Layouts *******************
     *
     */
    /**
     * Layout principal
     */
    private RelativeLayout mainLayout;
    /**
     * Layout del botón de play
     */
    private FrameLayout buttonLayout;
    /**
     * Layout para el efecto reveal
     */
    protected FrameLayout revealLayout;
    /**
     * Layout de barra de color inferior
     */
    private RelativeLayout colorBarLayout;
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
     * Animación del botón de play
     */
    private AnimationSet playTransition;
    /**
     * Animación del botón de play
     */
    private AnimationSet backgroundTransition;
    /**
     * Animación para esconder el botón de play
     */
    private AnimationSet hidePlay;
    /**
     * Animación para mostrar el botón de play
     */
    private AnimationSet showPlay;
    /**
     * Animación para mostrar el botón de play cuando venimos desde Read
     */
    private AnimationSet showPlayFromRead;
    /**
     * Animación para mostrar el botón de play cuando venimos desde Read
     */
    private AnimationSet showBackgroundFromRead;
    /**
     * Animación para esconder el botón de share
     */
    private AnimationSet hideShare;
    /**
     * Animación para mostrar el botón de share
     */
    private AnimationSet showShare;
    /**
     * Animación para esconder el botón de rate
     */
    private AnimationSet hideRate;
    /**
     * Animación para mostrar el botón de rate
     */
    private AnimationSet showRate;
    /**
     * Animación para esconder el botón de donate
     */
    private AnimationSet hideDonate;
    /**
     * Animación para mostrar el botón de donate
     */
    private AnimationSet showDonate;
    /**
     * Secuencia de animaciones que muestra el texto del botón share
     */
    private AnimationSet showShareText;
    /**
     * Secuencia de animaciones que muestra el texto del botón share
     */
    private AnimationSet hideShareText;
    /**
     * Secuencia de animaciones que muestra el texto del botón rate
     */
    private AnimationSet showRateText;
    /**
     * Secuencia de animaciones que muestra el texto del botón rate
     */
    private AnimationSet hideRateText;
    /**
     * Secuencia de animaciones que muestra el texto del botón donate
     */
    private AnimationSet showDonateText;
    /**
     * Secuencia de animaciones que muestra el texto del botón donate
     */
    private AnimationSet hideDonateText;
    /**
     * Animación para reajustar el tamaño del botón de fondo
     */
    private ScaleAnimation reduceBackgroundButton;
    /**
     * Animación para reajustar el tamaño del botón de fondo
     */
    private ScaleAnimation augmentBackgroundButton;
    /**
     * Animación de latido de botón de música
     */
    private AnimationSet heartBeatMusic;
    /**
     * Animación de latido de botón de Back
     */
    private Animation heartBeatBack;
    /**
     * Animación de latido de los botones de like
     */
    private Animation heartBeatShareText;
    /**
     * Animación de latido de los botones de like
     */
    private Animation heartBeatDonateText;
    /**
     * Animación de latido de los botones de like
     */
    private Animation heartBeatRateText;
    /**
     * Animación de latido de los botones de like
     */
    private Animation heartBeatShare;
    /**
     * Animación de latido de los botones de like
     */
    private Animation heartBeatDonate;
    /**
     * Animación de latido de los botones de like
     */
    private Animation heartBeatRate;
    /**
     * Animación de latido de botón de bacgorund
     */
    private Animation heartBeatBackground;

    /*
     ******************* Distancias *******************
     */
    /**
     * Contiene la distancia que se tiene que desplazar en botón de play en la animación
     */
    private float desplazamientoPlay;
     /*
     ******************* Flags *******************
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
     * Controla que la música siga sonando al terminar la actividad. En caso de pasar a
     * otro menú seguirá sonando.
     */
    private boolean continue_music = false;

    /**
     * Control de gesto para esconder barras de status y navegación.
     */
    private GestureDetector gestureDetector;
    /**
     * Vista para gestionar los cambios de visibilidad de las barras de status y nav
     */
    private View mDecorView;

    /**
     * Contexto
     */
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(Constants.Log.METHOD, "LikeActivity - onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_like);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.pink_dark, null));
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.pink_dark));
        }

        mContext = this;
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

                Log.w(Constants.Log.CONTROLS, "LikeActivity - NavigationBar - "+visible);
                Log.w(Constants.Log.CONTROLS, "LikeActivity - StatusBar - "+visibleStatus);
            }
        });

        buttonLayout = (FrameLayout) findViewById(R.id.layout_button);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        revealLayout = (FrameLayout) findViewById(R.id.revealLayout);
        logoImage = (ImageView) findViewById(R.id.logo_like);
        logoTextView = (TextView) findViewById(R.id.like_text);
        logoSubTextView = (TextView) findViewById(R.id.like_sub_text);
        colorBarLayout = (RelativeLayout) findViewById(R.id.color_bar_layout);
        shareTextView = (TextView) findViewById(R.id.like_share_text);
        rateTextView = (TextView) findViewById(R.id.like_rate_text);
        donateTextView = (TextView) findViewById(R.id.like_donate_text);

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

                music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_like, null));
            }
            else {
                music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off_like));
            }
        }
        else {

            setMusicIconOff();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_disabled_like, null));
            }
            else {
                music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on_like));
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

        //Inicializamos botón share
        share_btn = (ImageButton ) findViewById(R.id.share_button);

        //Inicializamos botón rate
        rate_btn = (ImageButton ) findViewById(R.id.rate_button);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Le damos forma circular
            ViewOutlineProvider outline_rate = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_divide_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };

            rate_btn.setOutlineProvider(outline_rate);
            rate_btn.setClipToOutline(true);
        }

        //Inicializamos botón donate
        donate_btn = (ImageButton ) findViewById(R.id.donate_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Le damos forma circular
            ViewOutlineProvider outline_donate = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_divide_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };

            donate_btn.setOutlineProvider(outline_donate);
            donate_btn.setClipToOutline(true);
        }

        //Inicializamos el botón de play
        play_btn = (ImageButton ) findViewById(R.id.play_button);

        //Inicializamos el botón de background
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

        prepareOnClicks();
        prepareAnimations();

        Intent intent = getIntent();

        //Cargamos música según vengamos de Like o Read
        if (intent.getIntExtra(Constants.LIKE_ACTIVITY_FROM, 0) ==
                Constants.LIKE_ACTIVITY_FROM_READ) {

            //Ponemos en marcha música
            //Restauramos valor por defecto para que se pare la música en onStop
            continue_music = false;
            MusicManager.createMusic(true);
            //Recodar el estado del volumen
            if (MusicManager.wasMainMusicOn()) {

                MusicManager.setMusicVolumeOn();
                MusicManager.setMusic_on(true);
            } else {

                MusicManager.setMusicVolumeOff();
                MusicManager.setMusic_on(false);
            }
            //MusicManager.startResumeMusic();
        }
        //Venimos de Main
        else {

            //En caso de que haya habido un onStop justo antes de entrar
            //Porque no creamos el MusicManager en onCreate siempre, solo cuando viene de Read.
            MusicManager.createMusicRestart(true);
        }

        //Necesitamos esperar al layout para mostrar el botón de play si venimos de Read
        mainLayout.post(new Runnable() {
            @Override
            public void run() {

                Intent intent = getIntent();

                //Según vengamos de Read o Main
                if (intent.getIntExtra(Constants.LIKE_ACTIVITY_FROM, 0) ==
                        Constants.LIKE_ACTIVITY_FROM_READ) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        play_btn.startAnimation(showPlayFromRead);
                        background_btn.startAnimation(showBackgroundFromRead);
                        startShowLayout();
                    }
                    else {

                        play_btn.startAnimation(showPlayFromRead);
                        background_btn.startAnimation(showBackgroundFromRead);
                        startShowLayoutBL();
                    }
                }
                //Venimos de Main
                else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        startShowLayout();
                    }
                    else {

                        startShowLayoutBL();
                    }
                }
            }
        });

    }

    private void prepareAnimations() {

        prepareStandardAnimations();
        prepareHideShowLayoutAnimations();
        prepareSocialButtonsAnimations();
    }

    /**
     * Prepara los eventos onClick
     */
    private void prepareOnClicks() {

        //OnClicks
        music_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - onClick musicButton");

                if (!areButtonsLocked()) {

                    //Bloqueamos el botón de play y el tap
                    lockButtons();

                    //Paramos animación latido y la reanudamos cuando termina de la animación de cambio de icono
                    music_btn.clearAnimation();
                    music_btn.startAnimation(clickMusic);

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
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off_like, null);
                        }
                        else {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_on_off);

                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off_like);
                        }

                        music_btn.setImageDrawable(frameAnimation);
                        music_btn.setBackground(transition);
                        transition.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));
                        frameAnimation.start();

                        //Quitamos volumen de la música.
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
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on_like, null);
                        }
                        else {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_off_on);

                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on_like);
                        }


                        music_btn.setImageDrawable(frameAnimation);
                        music_btn.setBackground(transition);
                        transition.startTransition(getResources().getInteger(R.integer.tool_btn_color_transition));
                        frameAnimation.start();

                        //Restablecemos volumen de la música.
                        MusicManager.setMusicVolumeOn();
                    }
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - onClick backButton");

                if (!areButtonsLocked()) {

                    lockButtons();

                    back_btn.startAnimation(clickBack);
                    startLikeButtonsToPlay();
                }
            }
        });
    }

    /**
     * Prepara la transición final del botón de play al volver de la actividad de lectura
     */
    private void preparePlayTransition() {

        //Cogemos la distancia antes de modificar la posición del botón de play
        desplazamientoPlay = play_btn.getY() + (play_btn.getHeight() / 2) - (mainLayout.getHeight() / 2);

        //Junta el desplazamiento del botón de play como la restauración de su tamaño después del PreReveal
        playTransition = new AnimationSet(true);

        /**
         * Esto supone un ligero salto en la animación ya que el play está a 1.1 y pasa a 1.0.
         * Esto se produce dado que es diferente cambiar el tamaño del botón en propiedades,
         * en donde se amplia el radio pero no el símbolo de play que mantiene
         * sus dimensiones, en cambio al hacer la animación scale el símbolo si se amplia.
         */
        ScaleAnimation adjustPlayTransition = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        adjustPlayTransition.setDuration(getResources().getInteger(R.integer.translate_return));

        TranslateAnimation transitionReturn = new TranslateAnimation(0, 0,
                0, desplazamientoPlay);
        transitionReturn.setDuration(getResources().getInteger(R.integer.translate_return));

        //playTransition.addAnimation(adjustPlayTransition);
        playTransition.addAnimation(transitionReturn);

        playTransition.setFillAfter(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            playTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        playTransition.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.METHOD, "LikeActivity - playTransition.onAnimationStart");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    play_btn.setElevation(getResources().getDimension(R.dimen.button_over_elevation));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.METHOD, "LikeActivity - playTransition.onAnimationEnd");

                //Fix absurdo. Evita flick en el botón al cambiar su posición con setLayoutParams dentro de onAnimationEnd
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                play_btn.startAnimation(animation);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) play_btn.getLayoutParams();
                params.width = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
                params.height = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
                params.gravity = Gravity.CENTER | Gravity.BOTTOM;
                params.bottomMargin = (int) getResources().getDimension(R.dimen.margin_play_btn_big);

                play_btn.setLayoutParams(params);

                //play_btn.clearAnimation();
                startPlayToLikeButtons();
            }
        });

        playTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));

        //Junta el desplazamiento del botón de play como la restauración de su tamaño después del PreReveal
        backgroundTransition = new AnimationSet(true);

        /**
         * Esto supone un ligero salto en la animación ya que el play está a 1.1 y pasa a 1.0.
         * Esto se produce dado que es diferente cambiar el tamaño del botón en propiedades,
         * en donde se amplia el radio pero no el símbolo de play que mantiene
         * sus dimensiones, en cambio al hacer la animación scale el símbolo si se amplia.
         */
        ScaleAnimation adjustBackgroundTransition = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        adjustBackgroundTransition.setDuration(getResources().getInteger(R.integer.translate_return));

        TranslateAnimation backgroundTransitionReturn = new TranslateAnimation(0, 0,
                0, desplazamientoPlay);
        backgroundTransitionReturn.setDuration(getResources().getInteger(R.integer.translate_return));

        backgroundTransition.addAnimation(adjustBackgroundTransition);
        backgroundTransition.addAnimation(backgroundTransitionReturn);

        backgroundTransition.setFillAfter(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            backgroundTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        backgroundTransition.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.METHOD, "LikeActivity - backgroundTransition.onAnimationStart");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    background_btn.setElevation(getResources().getDimension(R.dimen.button_higher_elevation));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.METHOD, "LikeActivity - backgroundTransition.onAnimationEnd");

                //Fix absurdo. Evita flick en el botón al cambiar su posición con setLayoutParams dentro de onAnimationEnd
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                background_btn.startAnimation(animation);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) background_btn.getLayoutParams();
                params.width = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
                params.height = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
                params.gravity = Gravity.CENTER | Gravity.BOTTOM;
                params.bottomMargin = (int) getResources().getDimension(R.dimen.margin_play_btn_big);

                background_btn.setLayoutParams(params);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    ViewOutlineProvider outline_play = new ViewOutlineProvider() {

                        @Override
                        public void getOutline(View view, Outline outline) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                                int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
                                outline.setOval(0, 0, diameter, diameter);
                            }
                        }
                    };

                    background_btn.setOutlineProvider(outline_play);
                    background_btn.setClipToOutline(true);
                }

            }
        });

        backgroundTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
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
                Log.v(Constants.Log.CONTROLS, "LikeActivity - clickMusic.onAnimationEnd");

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

        //Cargamos animación de latido para el botón de like
        heartBeatBack = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatBack.setRepeatMode(Animation.REVERSE);
        heartBeatBack.setRepeatCount(Animation.INFINITE);
        heartBeatBack.setDuration(getResources().getInteger(R.integer.heart_beat_back));

        //Cargamos animación de latido para el botones de créditos
        heartBeatShareText = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatShareText.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        heartBeatShareText.setRepeatMode(Animation.REVERSE);
        heartBeatShareText.setRepeatCount(Animation.INFINITE);

        //Cargamos animación de latido para el botones de créditos
        heartBeatDonateText = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatDonateText.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        heartBeatDonateText.setRepeatMode(Animation.REVERSE);
        heartBeatDonateText.setRepeatCount(Animation.INFINITE);

        //Cargamos animación de latido para el botones de créditos
        heartBeatRateText = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatRateText.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        heartBeatRateText.setRepeatMode(Animation.REVERSE);
        heartBeatRateText.setRepeatCount(Animation.INFINITE);

        //Cargamos animación de latido para el botones de créditos
        heartBeatShare = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatShare.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        heartBeatShare.setRepeatMode(Animation.REVERSE);
        heartBeatShare.setRepeatCount(Animation.INFINITE);

        //Cargamos animación de latido para el botones de créditos
        heartBeatDonate = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatDonate.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        heartBeatDonate.setRepeatMode(Animation.REVERSE);
        heartBeatDonate.setRepeatCount(Animation.INFINITE);

        //Cargamos animación de latido para el botones de créditos
        heartBeatRate = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatRate.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        heartBeatRate.setRepeatMode(Animation.REVERSE);
        heartBeatRate.setRepeatCount(Animation.INFINITE);

        //Cargamos animación de latido para el botón de back
        float coefReduce = ((float) getResources().getDimension(R.dimen.diameter_divide_btn)) /
                ((float) getResources().getDimension(R.dimen.diameter_play_btn_big));

        heartBeatBackground = new ScaleAnimation(coefReduce, coefReduce * 1.1f,
                coefReduce, coefReduce * 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatBackground.setRepeatMode(Animation.REVERSE);
        heartBeatBackground.setRepeatCount(Animation.INFINITE);
        heartBeatBackground.setDuration(getResources().getInteger(R.integer.heart_beat_slow));

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
                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideMusic.onAnimationStart");

                music_btn.setClickable(false);
                music_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideMusic.onAnimationEnd");
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
                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideBack.onAnimationStart");

                back_btn.setClickable(false);
                back_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideBack.onAnimationEnd");
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

                Log.v(Constants.Log.CONTROLS, "LikeActivity - colorBarOutTransition.onAnimationEnd");

                continue_music = true;

                //Finalizamos la actividad
                LikeActivity.this.finish();
                overridePendingTransition(0, 0);
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

                Log.v(Constants.Log.CONTROLS, "LikeActivity - colorBarInTransition.onAnimationEnd");

                //Utilizamos la animación adecuada según la actividad de la que vengamos
                Intent intent = getIntent();

                startPlayToLikeButtons();

                //Animaciones heartbeat
                music_btn.clearAnimation();
                if (MusicManager.isMusic_on()) music_btn.startAnimation(heartBeatMusic);
                back_btn.clearAnimation();
                back_btn.startAnimation(heartBeatBack);
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

        logoSubtextInTranstion.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoTextInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoImageInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        showMusic.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
        showBack.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
    }

    /**
     * Cargamos animaciones de los botones de créditos
     */
    private void prepareSocialButtonsAnimations() {

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

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hidePlay.onAnimationEnd");

                play_btn.setClickable(false);
                play_btn.setVisibility(View.INVISIBLE);
                share_btn.startAnimation(showShare);
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

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showPlay.onAnimationStart");

                play_btn.setVisibility(View.VISIBLE);
                play_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showPlay.onAnimationEnd");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    startHideLayout();
                }
                else {
                    startHideLayoutBL();
                }
            }
        });

        showPlayFromRead = new AnimationSet(true);

        ScaleAnimation scaleshowPlayFromRead = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleshowPlayFromRead.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        RotateAnimation rotateshowPlayFromRead = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateshowPlayFromRead.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        showPlayFromRead.addAnimation(scaleshowPlayFromRead);
        showPlayFromRead.addAnimation(rotateshowPlayFromRead);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showPlayFromRead.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showPlayFromRead.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showPlayFromRead.onAnimationStart");

                play_btn.setVisibility(View.VISIBLE);
                play_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showPlayFromRead.onAnimationEnd");
            }
        });

        showBackgroundFromRead = new AnimationSet(true);

        ScaleAnimation scaleshowBackgroundFromRead = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleshowBackgroundFromRead.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        RotateAnimation rotateshowBackgroundFromRead = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateshowBackgroundFromRead.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        showBackgroundFromRead.addAnimation(scaleshowBackgroundFromRead);
        showBackgroundFromRead.addAnimation(rotateshowBackgroundFromRead);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showBackgroundFromRead.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideShare = new AnimationSet(false);

        ScaleAnimation scaleHideShare = new ScaleAnimation(1, 0, 1f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideShare.setDuration(getResources().getInteger(R.integer.change_icon_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideShare.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        RotateAnimation rotateSharePlay = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rotateSharePlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        rotateSharePlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        hideShare.setStartOffset(getResources().getInteger(R.integer.delay_hide_share_btn));

        hideShare.addAnimation(rotateSharePlay);
        hideShare.addAnimation(scaleHideShare);

        hideShare.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideShare.onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideShare.onAnimationEnd");

                share_btn.setClickable(false);
                share_btn.setVisibility(View.INVISIBLE);
                play_btn.startAnimation(showPlay);
            }
        });

        showShare =  new AnimationSet(true);

        ScaleAnimation scaleShowShare = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowShare.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        RotateAnimation rotateShowShare = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateShowShare.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        showShare.addAnimation(scaleShowShare);
        showShare.addAnimation(rotateShowShare);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showShare.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showShare.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showShare.onAnimationStart");

                share_btn.setVisibility(View.VISIBLE);
                share_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showShare.onAnimationEnd");
            }
        });

        //Preparamos animación de mostrar texto del botón de share
        showShareText = new AnimationSet(true);

        AlphaAnimation alphaShowShareText = new AlphaAnimation(0, 1);
        alphaShowShareText.setDuration(getResources().getInteger(R.integer.show_social_text));

        ScaleAnimation scaleShowShareText = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowShareText.setDuration(getResources().getInteger(R.integer.show_social_text));

        TranslateAnimation translateShowShareText = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn), 0);
        translateShowShareText.setDuration(getResources().getInteger(R.integer.show_social_text));

        showShareText.addAnimation(alphaShowShareText);
        showShareText.addAnimation(scaleShowShareText);
        showShareText.addAnimation(translateShowShareText);

        showShareText.setStartOffset(getResources().getInteger(R.integer.delay_show_social_text));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showShareText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showShareText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showShareText.onAnimationStart");

                shareTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showShareText.onAnimationEnd");
            }
        });

        //Preparamos animación de ocultar texto del botón de share
        hideShareText = new AnimationSet(false);
		/*
		ScaleAnimation preScaleHideShareText = new ScaleAnimation(1, 1.3f, 1, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		preScaleHideShareText.setDuration(getResources().getInteger(R.integer.pre_hide_like_btns));
		preScaleHideShareText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
				getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
		*/
        AlphaAnimation alphaHideShareText = new AlphaAnimation(1, 0);
        alphaHideShareText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //alphaHideShareText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaHideShareText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideShareText = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideShareText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //scaleHideShareText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideShareText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        TranslateAnimation translateHideShareText = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn));
        translateHideShareText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //translateHideShareText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateHideShareText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //hideShareText.addAnimation(preScaleHideShareText);
        hideShareText.addAnimation(alphaHideShareText);
        hideShareText.addAnimation(scaleHideShareText);
        hideShareText.addAnimation(translateHideShareText);

        hideShareText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideShareText.onAnimationEnd");

                shareTextView.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar el botón de rate
        showRate = new AnimationSet(true);

        //Esto lo incluimos para que no sea visible antes de tiempo
        AlphaAnimation alphaShowRate = new AlphaAnimation(0, 1);
        alphaShowRate.setDuration(getResources().getInteger(R.integer.alpha_show_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaShowRate.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleShowRate = new ScaleAnimation(0.4f, 1, 0.4f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowRate.setDuration(getResources().getInteger(R.integer.show_social_btns));

        TranslateAnimation translateShowRate = new TranslateAnimation(
                getResources().getDimension(R.dimen.margin_horizontal_divide_btn), 0, 0, 0);
        translateShowRate.setDuration(getResources().getInteger(R.integer.show_social_btns));

        showRate.addAnimation(alphaShowRate);
        showRate.addAnimation(scaleShowRate);
        showRate.addAnimation(translateShowRate);

        showRate.setStartOffset(getResources().getInteger(R.integer.delay_show_social_btns));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showRate.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showRate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showRate.onAnimationStart");

                rate_btn.setVisibility(View.VISIBLE);
                rate_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showRate.onAnimationEnd");
            }
        });

        //Preparamos animación de ocultar el botón de rate
        hideRate = new AnimationSet(true);

        ScaleAnimation scaleRateHide = new ScaleAnimation(1, 0.4f, 1, 0.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleRateHide.setDuration(getResources().getInteger(R.integer.hide_social_btns));

        TranslateAnimation translateRateHide = new TranslateAnimation(0,
                getResources().getDimension(R.dimen.margin_horizontal_divide_btn), 0, 0);
        translateRateHide.setDuration(getResources().getInteger(R.integer.hide_social_btns));
        translateRateHide.setFillAfter(true);

        hideRate.setStartOffset(getResources().getInteger(R.integer.delay_hide_social_btns));

        hideRate.addAnimation(scaleRateHide);
        hideRate.addAnimation(translateRateHide);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideRate.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideRate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideRate.onAnimationEnd");

                rate_btn.setClickable(false);
                rate_btn.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar texto del botón de rate
        showRateText = new AnimationSet(true);

        AlphaAnimation alphaShowRateText = new AlphaAnimation(0, 1);
        alphaShowRateText.setDuration(getResources().getInteger(R.integer.show_social_text));

        ScaleAnimation scaleShowRateText = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowRateText.setDuration(getResources().getInteger(R.integer.show_social_text));

        TranslateAnimation translateShowRateText = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn), 0);
        translateShowRateText.setDuration(getResources().getInteger(R.integer.show_social_text));

        showRateText.addAnimation(alphaShowRateText);
        showRateText.addAnimation(scaleShowRateText);
        showRateText.addAnimation(translateShowRateText);

        showRateText.setStartOffset(getResources().getInteger(R.integer.delay_show_social_text));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showRateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showRateText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showRateText.onAnimationStart");

                rateTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showRateText.onAnimationEnd");

                share_btn.startAnimation(heartBeatShare);
                background_btn.startAnimation(heartBeatBackground);
                rate_btn.startAnimation(heartBeatRate);
                donate_btn.startAnimation(heartBeatDonate);
                rateTextView.startAnimation(heartBeatRateText);
                donateTextView.startAnimation(heartBeatDonateText);
                shareTextView.startAnimation(heartBeatShareText);
            }
        });

        //Preparamos animación de ocultar texto del botón de rate
        hideRateText = new AnimationSet(false);
		/*
		ScaleAnimation preScaleHideRateText = new ScaleAnimation(1, 1.3f, 1, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		preScaleHideRateText.setDuration(getResources().getInteger(R.integer.pre_hide_like_btns));
		preScaleHideRateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
				getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
		*/
        AlphaAnimation alphaHideRateText = new AlphaAnimation(1, 0);
        alphaHideRateText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //alphaHideRateText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaHideRateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideRateText = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideRateText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //scaleHideRateText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideRateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        TranslateAnimation translateHideRateText = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn));
        translateHideRateText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //translateHideRateText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateHideRateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //hideRateText.addAnimation(preScaleHideRateText);
        hideRateText.addAnimation(alphaHideRateText);
        hideRateText.addAnimation(scaleHideRateText);
        hideRateText.addAnimation(translateHideRateText);

        hideRateText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideRateText.onAnimationEnd");

                rateTextView.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar el botón de donate
        showDonate = new AnimationSet(true);

        //Esto lo incluimos para que no sea visible antes de tiempo
        AlphaAnimation alphaShowDonate = new AlphaAnimation(0, 1);
        alphaShowDonate.setDuration(getResources().getInteger(R.integer.alpha_show_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaShowDonate.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleDonateShow = new ScaleAnimation(0.4f, 1, 0.4f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleDonateShow.setDuration(getResources().getInteger(R.integer.show_social_btns));

        TranslateAnimation translateDonateShow = new TranslateAnimation(
                -getResources().getDimension(R.dimen.margin_horizontal_divide_btn), 0, 0, 0);
        translateDonateShow.setDuration(getResources().getInteger(R.integer.show_social_btns));
        translateDonateShow.setFillAfter(true);

        showDonate.addAnimation(alphaShowDonate);
        showDonate.addAnimation(scaleDonateShow);
        showDonate.addAnimation(translateDonateShow);

        showDonate.setStartOffset(getResources().getInteger(R.integer.delay_show_social_btns));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showDonate.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showDonate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showDonate.onAnimationStart");

                donate_btn.setVisibility(View.VISIBLE);
                donate_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showDonate.onAnimationEnd");
            }
        });

        //Preparamos animación de ocultar el botón de donate
        hideDonate = new AnimationSet(true);

        ScaleAnimation scaleHideDonate = new ScaleAnimation(1, 0.4f, 1, 0.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideDonate.setDuration(getResources().getInteger(R.integer.hide_social_btns));

        TranslateAnimation translateHideDonate = new TranslateAnimation(0,
                -getResources().getDimension(R.dimen.margin_horizontal_divide_btn), 0, 0);
        translateHideDonate.setDuration(getResources().getInteger(R.integer.hide_social_btns));
        translateHideDonate.setFillAfter(true);

        hideDonate.setStartOffset(getResources().getInteger(R.integer.delay_hide_social_btns));

        hideDonate.addAnimation(scaleHideDonate);
        hideDonate.addAnimation(translateHideDonate);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideDonate.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideDonate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideDonate.onAnimationEnd");

                donate_btn.setClickable(false);
                donate_btn.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar texto del botón de donate
        showDonateText = new AnimationSet(true);

        AlphaAnimation alphaShowDonateText = new AlphaAnimation(0, 1);
        alphaShowDonateText.setDuration(getResources().getInteger(R.integer.show_social_text));

        ScaleAnimation scaleShowDonateText = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowDonateText.setDuration(getResources().getInteger(R.integer.show_social_text));

        TranslateAnimation translateShowDonateText = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn), 0);
        translateShowDonateText.setDuration(getResources().getInteger(R.integer.show_social_text));

        showDonateText.addAnimation(alphaShowDonateText);
        showDonateText.addAnimation(scaleShowDonateText);
        showDonateText.addAnimation(translateShowDonateText);

        showDonateText.setStartOffset(getResources().getInteger(R.integer.delay_show_social_text));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showDonateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showDonateText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - showDonateText.onAnimationStart");

                donateTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
        });

        //Preparamos animación de ocultar texto del botón de donate
        hideDonateText = new AnimationSet(false);
		/*
		ScaleAnimation preScaleHideDonateText = new ScaleAnimation(1, 1.3f, 1, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		preScaleHideDonateText.setDuration(getResources().getInteger(R.integer.pre_hide_like_btns));
		preScaleHideDonateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
				getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
		*/
        AlphaAnimation alphaHideDonateText = new AlphaAnimation(1, 0);
        alphaHideDonateText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //alphaHideDonateText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaHideDonateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideDonateText = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideDonateText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //scaleHideDonateText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideDonateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        TranslateAnimation translateHideDonateText = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn));
        translateHideDonateText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //translateHideDonateText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateHideDonateText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //hideDonateText.addAnimation(preScaleHideDonateText);
        hideDonateText.addAnimation(alphaHideDonateText);
        hideDonateText.addAnimation(scaleHideDonateText);
        hideDonateText.addAnimation(translateHideDonateText);

        hideDonateText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "LikeActivity - hideDonateText.onAnimationEnd");

                donateTextView.setVisibility(View.INVISIBLE);
            }
        });

        float coefReduce = ((float) getResources().getDimension(R.dimen.diameter_divide_btn)) /
                ((float) getResources().getDimension(R.dimen.diameter_play_btn_big));

        reduceBackgroundButton = new ScaleAnimation(1,coefReduce, 1, coefReduce,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        reduceBackgroundButton.setDuration(getResources().getInteger(R.integer.reduce_background_btn_social));
        reduceBackgroundButton.setFillAfter(true);
        reduceBackgroundButton.setStartOffset(getResources().getInteger(R.integer.delay_reduce_background_social));

        augmentBackgroundButton = new ScaleAnimation(coefReduce, 1, coefReduce, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        augmentBackgroundButton.setDuration(getResources().getInteger(R.integer.augment_background_btn_social));
        augmentBackgroundButton.setFillAfter(true);
        augmentBackgroundButton.setStartOffset(getResources().getInteger(R.integer.delay_augment_background_social));
    }

    /**
     * Muestra las vistas de la interfaz
     */
    private void startShowLayout() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startShowLayout");

        lockButtons();

        logoTextView.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransition);
        //music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        //back_btn.startAnimation(showBack);
        back_btn.setVisibility(View.VISIBLE);
        back_btn.setClickable(true);

        startUnvealLike();

    }

    /**
     * Muestra las vistas de la interfaz para versiones anteriores a Lollipop
     */
    private void startShowLayoutBL() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startShowLayoutBL");

        lockButtons();

        revealLayout.setVisibility(View.INVISIBLE);

        colorBarLayout.startAnimation(colorBarInTransition);
        logoTextView.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransition);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        back_btn.startAnimation(showBack);
        back_btn.setVisibility(View.VISIBLE);
        back_btn.setClickable(true);

    }

    /**
     * Oculta las vistas de la interfaz
     */
    private void startHideLayout() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startHideLayout");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark, null));
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark));
        }

        //colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextView.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);
        music_btn.startAnimation(hideMusic);
        back_btn.startAnimation(hideBack);

        startRevealLikeReturn();
    }

    /**
     * Oculta las vistas de la interfaz
     */
    private void startHideLayoutBL() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startHideLayoutBL");

        colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextView.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);
        music_btn.startAnimation(hideMusic);
        back_btn.startAnimation(hideBack);
    }

    private void startPlayToLikeButtons() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startPlayToLikeButtons");

        //Mostramos nuevos botones
        play_btn.startAnimation(hidePlay);
        //Reducimos backgorund button
        background_btn.startAnimation(reduceBackgroundButton);
        //Mostramos los botones de créditos
        rate_btn.startAnimation(showRate);
        donate_btn.startAnimation(showDonate);
        //Texto sobre los botones con cierto delay
        shareTextView.startAnimation(showShareText);
        rateTextView.startAnimation(showRateText);
        donateTextView.startAnimation(showDonateText);

        //Programamos desbloqueo
        programUnlock(showRateText.getDuration()+showRateText.getStartOffset());
    }

    private void startLikeButtonsToPlay() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startLikeButtonsToPlay");

        //Escondemos texto
        shareTextView.startAnimation(hideShareText);
        rateTextView.startAnimation(hideRateText);
        donateTextView.startAnimation(hideDonateText);
        //Aumentamos background button
        background_btn.startAnimation(augmentBackgroundButton);
        //Los botones con su delay
        share_btn.startAnimation(hideShare);
        rate_btn.startAnimation(hideRate);
        donate_btn.startAnimation(hideDonate);
    }


    /**
     * Pone en marcha animación Reveal Effect en reverso. Utilizada al entrar en la actividad,
     * después de la primera parte realizada en LikeActivity.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUnvealLike() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startUnvealLike");

            //Calculamos el centro de la animación
            int cx = (mainLayout.getLeft() + mainLayout.getRight()) / 2;
            int cy = mainLayout.getBottom() - (int) getResources().getDimension(R.dimen.color_bar_height);

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
                    Log.v(Constants.Log.METHOD, "LikeActivity - startUnvealLike.onAnimationStart");

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.v(Constants.Log.METHOD, "LikeActivity - startUnvealLike.onAnimationEnd");

                    revealLayout.setVisibility(View.INVISIBLE);

                    //Animaciones heartbeat
                    music_btn.clearAnimation();
                    if (MusicManager.isMusic_on()) music_btn.startAnimation(heartBeatMusic);
                    back_btn.clearAnimation();
                    back_btn.startAnimation(heartBeatBack);

                    //Utilizamos la animación adecuada según la actividad de la que vengamos
                    Intent intent = getIntent();

                     startPlayToLikeButtons();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
            //anim.setStartDelay(getResources().getInteger(R.integer.delay_reveal_like));
            anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
            anim.start();
    }

    /**
     * Pone en marcha animación Reveal Effect al pulsar el botón de cerrar.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startRevealLikeReturn() {

        Log.v(Constants.Log.METHOD, "LikeActivity - startRevealLikeReturn");

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
                Log.v(Constants.Log.METHOD, "LikeActivity - startRevealLikeReturn.onAnimationStart");

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
                Log.v(Constants.Log.METHOD, "LikeActivity - startRevealLikeReturn.onAnimationEnd");

                //Finalizamos la actividad
                LikeActivity.this.finish();
                overridePendingTransition(0, 0);
                continue_music = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        //anim.setStartDelay(getResources().getInteger(R.integer.delay_reveal_standard_return));
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        anim.start();
    }

    /**
     * Pone el icono de música de la barra de controles según densidad de pixel.
     */
    private void setMusicIconOn() {

        Log.v(Constants.Log.METHOD, "LikeActivity - setMusicIconOn");

        final int mask = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (mask ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            music_btn.setImageResource(R.drawable.ic_music_note_white_18dp);
        }
        else if (mask ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_white_24dp);
        }
        else if (mask ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE) {
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

        Log.v(Constants.Log.METHOD, "LikeActivity - setMusicIconOff");

        final int mask = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if (mask ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_18dp);
        }
        else if (mask ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_24dp);
        }
        else if (mask ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_24dp);
        }
        else {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_18dp);
        }
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {

        Log.w(Constants.Log.METHOD, "LikeActivity - hideSystemUI");

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

        Log.i(Constants.Log.METHOD, "LikeActivity - lockButtons = "+btn_locked);
    }

    /**
     * Desbloquea el botón de play
     */
    public void unlockButtons() {

        btn_locked = false;

        Log.i(Constants.Log.METHOD, "LikeActivity - unlockButtons = "+btn_locked);
    }

    /**
     * Comprueba si el botón está bloqueado o no
     * @return
     */
    public boolean areButtonsLocked() {

        Log.v(Constants.Log.METHOD, "LikeActivity - Locked = " + btn_locked);

        return btn_locked;
    }

    /**
     * Programa desbloqueo dentro de "duration" ms.
     * @param duration
     */
    protected void programUnlock(long duration) {

        Log.i(Constants.Log.METHOD, "LikeActivity - programUnlock: "+duration);

        //Programamos desbloqueo
        new Handler().postDelayed(new Runnable() {
            public void run() {

                unlockButtons();
            }
        }, duration);
    }

    @Override
    protected void onResume() {
        Log.v(Constants.Log.METHOD, "LikeActivity - onResume");

        //Escondemos barras
        hideSystemUI();

        MusicManager.startResumeMusic();

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(Constants.Log.METHOD, "LikeActivity - onPause");

        //Pausamos música
        if (!continue_music) MusicManager.pauseMusic();

        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.v(Constants.Log.METHOD, "LikeActivity - onRestart");

        MusicManager.createMusicRestart(true);

        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.v(Constants.Log.METHOD, "LikeActivity - onStop");

        if (!continue_music) MusicManager.stopMusic();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(Constants.Log.METHOD, "LikeActivity - onDestroy");

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        Log.v(Constants.Log.METHOD, "LikeActivity - onBackPressed -");

        if (!areButtonsLocked()) {

            lockButtons();
            back_btn.startAnimation(clickBack);
            startLikeButtonsToPlay();
        }
    }
}
