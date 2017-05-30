package com.android.cuentacuentos;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Outline;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioManager;
import android.net.Uri;
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
import android.view.ViewTreeObserver;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Pattern;

import utils.Constants;
import utils.Constants.Options;
import utils.MusicManager;

import static utils.Constants.ACTIVITY_REQUEST_CODE_MANUAL;
import static utils.Constants.ACTIVITY_REQUEST_CODE_STANDARD;

public class MainActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    /**
     * Contexto
     */
    private Context mContext;
    /**
     * Control de gesto para esconder barras de status y navegación.
     */
    private GestureDetector gestureDetector;
    /*
     ******************* Vistas *******************
     */
    /**
     * Botón de play
     */
    private ImageButton manual_btn;
    /**
     * Botón de play
     */
    private ImageButton autoplay_btn;
    /**
     * Botón de play
     */
    private ImageButton play_btn;
    /**
     * Cuadro de texto secundario
     */
    private TextView manualTextView;
    /**
     * Cuadro de texto secundario
     */
    private TextView autoplayTextView;
    /**
     * Cuadro de texto secundario
     */
    private TextView playTextView;
    /**
     * Botón de audio
     */
    private ImageButton music_btn;
    /**
     * Botón de like
     */
    private ImageButton like_btn;
    /**
     * Botón de info del menú
     */
    private ImageButton info_btn;
    /**
     * Cuadro de texto secundario
     */
    private TextView logoSubTextView;
    /**
     * Imagen del logo
     */
    private ImageView logoImage;
    /**
     * Imagen del logo de reveal
     */
    private ImageView revealImage;
    /**
     * Texto del reveal
     */
    private TextView revealText;
    /*
     ******************* Layouts *******************
     */
    /**
     * Layout principal
     */
    private RelativeLayout mainLayout;
    /**
     * Layout reveal
     */
    private FrameLayout revealLayout;
    /**
     * Layout del botón de play
     */
    private FrameLayout buttonLayout;
    /**
     * Layout de barra de color inferior
     */
    private RelativeLayout colorBarLayout;
    /**
     * Layout de barra de texto del logo
     */
    private LinearLayout logoLayout;
    /**
     * Layout de barra de texto del logo
     */
    private LinearLayout logoTextLayout;
    /**
     * Layout de barra de texto del logo
     */
    private RelativeLayout leftControlLayout;

    /*
     ******************* Distancias *******************
     */
    /**
     * Contiene la distancia que se tiene que desplazar en botón de play en la animación
     */
    private float desplazamientoPlay;
    /**
     * Contiene la distancia que se tiene que desplazar en botón de like en la animación
     */
    private float desplazamientoVerticalLike;
    /**
     * Contiene la distancia que se tiene que desplazar en botón de like en la animación
     */
    private float desplazamientoHorizontalLike;

    /*
     ******************* Animaciones *******************
     */
    /**
     * Animación del botón de música
     */
    private AnimationSet clickMusic;
    /**
     * Animación aparición botón de música
     */
    private Animation showMusic;
    /**
     * Animación aparición botón de créditos
     */
    private Animation showInfo;
    /**
     * Animación aparición botón de like
     */
    private Animation showLike;
    /**
     * Animación ocultación botón de música
     */
    private Animation hideMusic;
    /**
     * Animación ocultación botón de info
     */
    private Animation hideInfo;
    /**
     * Animación ocultación botón de info en sentido contrario
     */
    private Animation hideInfoReverse;
    /**
     * Animación ocultación botón de like
     */
    private Animation hideLike;
    /**
     * Animación ocultación botón de música
     */
    private Animation transitionHideBack;
    /**
     * Animación ocultación botón de like
     */
    private Animation translationShowBack;
    /**
     * Animación de latido de botón de música
     */
    private AnimationSet heartBeatMusic;
    /**
     * Animación de latido de botón de Like
     */
    private Animation heartBeatLike;
    /**
     * Animación de latido de botón de Info
     */
    private Animation heartBeatInfo;
    /**
     * Animación del bóton de play antes del reveal
     */
    private AnimationSet preRevealPlay;
    /**
     * Animación para ajustar el tamaño del botón de play antes de cambiar de actividad.
     */
    private ScaleAnimation playEndHeartbeat;
    /**
     * Animación del bóton de play antes del reveal. 2a parte.
     */
    private AnimationSet playTransition;
    /**
     * Animación del botón de play de retorno
     */
    private AnimationSet playTransitionReturn;
    /**
     * Animación del bóton de like
     */
    private AnimationSet hideLikeForTransition;
    /**
     * Animación del bóton de info
     */
    private AnimationSet hideInfoForReveal;
    /**
     * Animación del bóton de play al pulsarlo
     */
    private ScaleAnimation clickPlay;
    /**
     * Animación del bóton de like al pulsarlo
     */
    private ScaleAnimation clickLike;
    /**
     * Animación del bóton de info al pulsarlo
     */
    private ScaleAnimation clickInfo;
    /**
     * Animación del bóton de like. 2a parte.
     */
    private Animation showLikeForReveal;
    /**
     * Animación del bóton de back al pulsarlo
     */
    private Animation animBackClick;
    /**
     * Animación para mostrar el botón de play
     */
    private AnimationSet showPlay;
    /**
     * Animación para esconder el botón de play
     */
    private AnimationSet hidePlay;
    /**
     * Animación para mostrar el botón de Autoplay
     */
    private AnimationSet showAutoplay;
    /**
     * Animación para esconder el botón de autoplay
     */
    private AnimationSet hideAutoplay;
    /**
     * Animación para mostrar el botón de manual
     */
    private AnimationSet showManual;
    /**
     * Animación para esconder el botón de manual
     */
    private AnimationSet hideManual;
    /**
     * Animación de latido de botón de play
     */
    private Animation heartBeatPlay;
    /**
     * Animación de latido de botón de play
     */
    private Animation heartBeatPlayText;
    /**
     * Animación de latido de botón de play
     */
    private Animation heartBeatAutoplay;
    /**
     * Animación de latido de botón de play
     */
    private Animation heartBeatAutoplayText;
    /**
     * Animación de latido de botón de play
     */
    private Animation heartBeatManual;
    /**
     * Animación de latido de botón de play
     */
    private Animation heartBeatManualText;
    /**
     * Secuencia de animaciones que muestra el texto del botón rate
     */
    private AnimationSet showAutoplayText;
    /**
     * Secuencia de animaciones que muestra el texto del botón share
     */
    private AnimationSet hideAutoplayText;
    /**
     * Secuencia de animaciones que muestra el texto del botón share
     */
    private AnimationSet showPlayText;
    /**
     * Secuencia de animaciones que muestra el texto del botón rate
     */
    private AnimationSet hidePlayText;
    /**
     * Secuencia de animaciones que muestra el texto del botón donate
     */
    private AnimationSet showManualText;
    /**
     * Secuencia de animaciones que muestra el texto del botón donate
     */
    private AnimationSet hideManualText;
    /**
     * Animaciones de transiciones a la siguiente actividad
     */
    private TranslateAnimation colorBarOutTransition;
    private TranslateAnimation logoTextOutTransition;
    private TranslateAnimation logoSubtextOutTransition;
    private TranslateAnimation logoImageOutTransition;
    private TranslateAnimation logoImageOutTransitionReverse;
    private TranslateAnimation colorBarInTransition;
    private TranslateAnimation logoTextInTransition;
    private TranslateAnimation logoSubtextInTranstion;
    private TranslateAnimation logoImageInTransition;
    private TranslateAnimation logoImageInTransitionReverse;

    /*
     ******************* Flags *******************
     */
    /**
     * Indicador de a que actividad nos dirigimos
     */
    private int toActivity;
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
     * Vista para gestionar los cambios de visibilidad de las barras de status y nav
     */
    private View mDecorView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(Constants.Log.METHOD, "MainActivity - onCreate ");

        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.activity_main);

        //Preparamos el audio
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        // Request audio focus for playback
        int result = am.requestAudioFocus(this,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // could not get audio focus.
            Log.e(Constants.Log.AUDIO, "MainActivity onCreate - Audio Focus no conseguido.");
        }
        MusicManager.build(mContext);
        MusicManager.createMusic(true);
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

                Log.w(Constants.Log.CONTROLS, "MainActivity - NavigationBar - " + visible);
                Log.w(Constants.Log.CONTROLS, "MainActivity - StatusBar - " + visibleStatus);
            }
        });

        buttonLayout = (FrameLayout) findViewById(R.id.layout_button);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        logoLayout = (LinearLayout) findViewById(R.id.logo_layout);
        revealLayout = (FrameLayout) findViewById(R.id.revealLayout);
        colorBarLayout = (RelativeLayout) findViewById(R.id.color_bar_layout);
        logoTextLayout = (LinearLayout) findViewById(R.id.logo_text_layout);
        leftControlLayout = (RelativeLayout) findViewById(R.id.controls_top_left);
        logoSubTextView = (TextView) findViewById(R.id.logo_sub_text);
        logoImage = (ImageView) findViewById(R.id.logo_image);

        //Textviews de botones
        manualTextView = (TextView) findViewById(R.id.manual_text);
        autoplayTextView = (TextView) findViewById(R.id.autoplay_text);
        playTextView = (TextView) findViewById(R.id.play_text);

        //Reveal icon
        revealImage = (ImageView) findViewById(R.id.reveal_image);
        revealText = (TextView) findViewById(R.id.tview_reveal);

        //Inicializamos botones

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

        //Inicializamos el botón de like
        like_btn = (ImageButton) findViewById(R.id.btn_like);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Le damos forma circular
            ViewOutlineProvider outline_like_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_logo_btns);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            like_btn.setOutlineProvider(outline_like_btn);
            like_btn.setClipToOutline(true);
        }


        //Inicializamos el botón de back
        info_btn = (ImageButton) findViewById(R.id.btn_info);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Le damos forma circular
            ViewOutlineProvider outline_info_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_logo_btns);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            info_btn.setOutlineProvider(outline_info_btn);
            info_btn.setClipToOutline(true);
        }

        //Inicializamos el botón de música
        manual_btn = (ImageButton) findViewById(R.id.manual_button);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Le damos forma circular
            ViewOutlineProvider outline_music_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_divide_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            manual_btn.setOutlineProvider(outline_music_btn);
            manual_btn.setClipToOutline(true);
        }

        //Inicializamos el botón de música
        autoplay_btn = (ImageButton) findViewById(R.id.autoplay_button);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Le damos forma circular
            ViewOutlineProvider outline_music_btn = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_divide_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };
            autoplay_btn.setOutlineProvider(outline_music_btn);
            autoplay_btn.setClipToOutline(true);
        }

        //Inicializamos el botón de play
        play_btn = (ImageButton) findViewById(R.id.play_button);

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

            play_btn.setOutlineProvider(outline_play);
            play_btn.setClipToOutline(true);
        }


        //Preparamos las animaciones
        //Las que necesitan esperar al layout
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                /*
                desplazamientoPlay = play_btn.getY() + (play_btn.getHeight() / 2) - (mainLayout.getHeight() / 2);
                preparePlayTransition();
                */
                int expectedWidth = getResources().getInteger(R.integer.expected_resolution_width);
                int expectedHeight = getResources().getInteger(R.integer.expected_resolution_height);

                /*
                Log.d(Constants.Log.DEBUG, "MainActivity - expectedWidth = "+expectedWidth);
                Log.d(Constants.Log.DEBUG, "MainActivity - expectedHeight = "+expectedHeight);
                Log.d(Constants.Log.DEBUG, "MainActivity - measuredWidth = "+mainLayout.getMeasuredWidth());
                Log.d(Constants.Log.DEBUG, "MainActivity - measuredHeight = "+mainLayout.getMeasuredHeight());
                */

                if (true) {

                    //Decodificación de imágenes
                    ReadActivity.decodeScaleAndSaveAllPages(
                            mainLayout.getMeasuredWidth(), mainLayout.getMeasuredHeight(), mContext);
                }

                //Hacemos visible el menú
                startUnvealInicio();
            }
        });

        //Resto
        prepareAnimations();

        //Eventos onClick
        prepareOnClicks();

        //Animaciones heartbeat
        startHeartBeats();

        //Animación bucle logo
        /*
        AnimationDrawable frameAnimation = (AnimationDrawable) logoImage.getDrawable();
        frameAnimation.start();
        */

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Empieza las animaciones heartbeat
     */
    private void startHeartBeats() {

        Log.v(Constants.Log.CONTROLS, "MainActivity - startHeartBeats ");

        if (MusicManager.isMusic_on()) {

            music_btn.clearAnimation();
            music_btn.startAnimation(heartBeatMusic);
        }
        //like_btn.clearAnimation();
        like_btn.startAnimation(heartBeatLike);
        //info_btn.clearAnimation();
        info_btn.startAnimation(heartBeatInfo);
        //play_btn.clearAnimation();
        play_btn.startAnimation(heartBeatPlay);
        //playTextView.clearAnimation();
        playTextView.startAnimation(heartBeatPlayText);
        //autoplay_btn.clearAnimation();
        autoplay_btn.startAnimation(heartBeatAutoplay);
        //autoplayTextView.clearAnimation();
        autoplayTextView.startAnimation(heartBeatAutoplayText);
        //manual_btn.clearAnimation();
        manual_btn.startAnimation(heartBeatManual);
        //manualTextView.clearAnimation();
        manualTextView.startAnimation(heartBeatManualText);
    }

    /**
     * Limpiamos animaciones previas. Se utiliza principalmente al volver a la actividad.
     * Al no llamar a onCreate puede que ciertas vistas conserven estados diferentes al original.
     */
    private void clearAnimations() {

        like_btn.clearAnimation();
        info_btn.clearAnimation();
        music_btn.clearAnimation();
        autoplay_btn.clearAnimation();
        play_btn.clearAnimation();
        manual_btn.clearAnimation();
        autoplayTextView.clearAnimation();
        playTextView.clearAnimation();
        manualTextView.clearAnimation();
        logoImage.clearAnimation();
        logoSubTextView.clearAnimation();
        revealImage.clearAnimation();
        revealText.clearAnimation();
    }

    /**
     * Preparamos los eventos onClick
     */
    private void prepareOnClicks() {

        music_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - onClick musicButton ");

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
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off, null);
                        } else {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_on_off);

                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off);
                        }

                        music_btn.setImageDrawable(frameAnimation);
                        music_btn.setBackground(transition);
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
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on, null);
                        } else {

                            frameAnimation =
                                    (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim_music_off_on);

                            transition =
                                    (TransitionDrawable) getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on);
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
        like_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - onClick likeButton ");

                if (!areButtonsLocked()) {

                    lockButtons();

                    toActivity = Constants.ACTIVITY_REQUEST_CODE_LIKE;

                    startMergeMainButtons();
                    like_btn.clearAnimation();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        like_btn.startAnimation(hideLikeForTransition);
                    } else {

                        like_btn.startAnimation(clickLike);
                    }
                }
            }
        });
        info_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - onClick infoButton ");

                if (!areButtonsLocked()) {

                    lockButtons();

                    toActivity = Constants.ACTIVITY_REQUEST_CODE_INFO;

                    startMergeMainButtons();
                    info_btn.clearAnimation();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        info_btn.startAnimation(hideInfoForReveal);
                    } else {

                        info_btn.startAnimation(clickInfo);
                    }
                }
            }
        });
        autoplay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - onClick autoplayButton ");

                if (!areButtonsLocked()) {

                    lockButtons();

                    toActivity = Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY;

                    prepareRevealIcon();

                    autoplay_btn.clearAnimation();
                    //autoplay_btn.startAnimation(preRevealPlay);
                    autoplay_btn.startAnimation(clickPlay);
                    MusicManager.fadeMusic();
                }
            }
        });
        manual_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - onClick manualButton ");

                if (!areButtonsLocked()) {

                    lockButtons();

                    toActivity = Constants.ACTIVITY_REQUEST_CODE_MANUAL;

                    prepareRevealIcon();

                    manual_btn.clearAnimation();
                    //autoplay_btn.startAnimation(preRevealPlay);
                    manual_btn.startAnimation(clickPlay);
                    MusicManager.fadeMusic();
                }
            }
        });
        play_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - onClick playButton ");

                if (!areButtonsLocked()) {

                    lockButtons();

                    toActivity = Constants.ACTIVITY_REQUEST_CODE_STANDARD;

                    prepareRevealIcon();

                    play_btn.clearAnimation();
                    //play_btn.startAnimation(preRevealPlay);
                    play_btn.startAnimation(clickPlay);
                    //startMergeMainButtons();
                    MusicManager.fadeMusic();
                }
            }
        });
    }


    /**
     * Prepara el icono del reveal correspondiente
     */
    private void prepareRevealIcon() {

        final int mask = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (toActivity) {

            case Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY: {

                revealText.setText(getResources().getString(R.string.main_autoplay_text));

                if (mask ==Configuration.SCREENLAYOUT_SIZE_NORMAL) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_36dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_36dp));
                    }
                }
                else if (mask == Configuration.SCREENLAYOUT_SIZE_LARGE ) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_48dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_48dp));
                    }
                }
                else if (mask == Configuration.SCREENLAYOUT_SIZE_XLARGE ) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_48dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_48dp));
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_36dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause_white_36dp));
                    }
                }

                break;
            }

            case Constants.ACTIVITY_REQUEST_CODE_MANUAL: {

                revealText.setText(getResources().getString(R.string.main_manual_text));

                if (mask == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_36dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_36dp));
                    }
                }
                else if (mask ==  Configuration.SCREENLAYOUT_SIZE_LARGE) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_48dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_48dp));
                    }
                }
                else if (mask ==  Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_48dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_48dp));
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_36dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_book_open_page_variant_white_36dp));
                    }
                }

                break;
            }

            case Constants.ACTIVITY_REQUEST_CODE_STANDARD: {

                revealText.setText(getResources().getString(R.string.main_play_text));

                if (mask == Configuration.SCREENLAYOUT_SIZE_NORMAL) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_36dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_36dp));
                    }
                }
                else if (mask == Configuration.SCREENLAYOUT_SIZE_LARGE ) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_48dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_48dp));
                    }
                }
                else if (mask == Configuration.SCREENLAYOUT_SIZE_XLARGE ) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_48dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_48dp));
                    }
                }
                else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_36dp, null));
                    } else {
                        revealImage.
                                setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_36dp));
                    }
                }
                break;
            }
        }
    }

    /**
     * Preparamos las animaciones
     */
    private void prepareAnimations() {

        prepareStandardAnimations();
        prepareTransitionToReadAnimations();
        prepareTransitionToLikeAnimations();
        prepareTransitionToInfoAnimations();
        prepareHideShowLayoutAnimations();
        prepareMergeDivideMainButtonsAnimations();
    }

    /**
     * Prepara animaciones sin clasificar. Heartbeats, clicks...
     */
    private void prepareStandardAnimations() {

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
                Log.v(Constants.Log.CONTROLS, "MainActivity - clickMusic.onAnimationEnd ");

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

        //Cargamos animación de latido para el botón de play
        heartBeatPlay = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatPlay.setRepeatMode(Animation.REVERSE);
        heartBeatPlay.setRepeatCount(Animation.INFINITE);
        heartBeatPlay.setDuration(getResources().getInteger(R.integer.heart_beat_play));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatPlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de play
        heartBeatPlayText = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatPlayText.setRepeatMode(Animation.REVERSE);
        heartBeatPlayText.setRepeatCount(Animation.INFINITE);
        heartBeatPlayText.setDuration(getResources().getInteger(R.integer.heart_beat_play));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatPlayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de play
        heartBeatAutoplay = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatAutoplay.setRepeatMode(Animation.REVERSE);
        heartBeatAutoplay.setRepeatCount(Animation.INFINITE);
        heartBeatAutoplay.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatAutoplay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de play
        heartBeatAutoplayText = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatAutoplayText.setRepeatMode(Animation.REVERSE);
        heartBeatAutoplayText.setRepeatCount(Animation.INFINITE);
        heartBeatAutoplayText.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatAutoplayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de play
        heartBeatManual = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatManual.setRepeatMode(Animation.REVERSE);
        heartBeatManual.setRepeatCount(Animation.INFINITE);
        heartBeatManual.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatManual.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de play
        heartBeatManualText = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatManualText.setRepeatMode(Animation.REVERSE);
        heartBeatManualText.setRepeatCount(Animation.INFINITE);
        heartBeatManualText.setDuration(getResources().getInteger(R.integer.heart_beat_slow));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatManualText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos animación de latido para el botón de música
        heartBeatMusic = new AnimationSet(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            heartBeatMusic.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation heartBeatMusicScale = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
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
        heartBeatLike = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatLike.setRepeatMode(Animation.REVERSE);
        heartBeatLike.setRepeatCount(Animation.INFINITE);
        heartBeatLike.setDuration(getResources().getInteger(R.integer.heart_beat_slow));

        //Cargamos animación de latido para el botón de like
        heartBeatInfo = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        heartBeatInfo.setRepeatMode(Animation.REVERSE);
        heartBeatInfo.setRepeatCount(Animation.INFINITE);
        heartBeatInfo.setDuration(getResources().getInteger(R.integer.heart_beat_slow));

        playEndHeartbeat = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        playEndHeartbeat.setDuration(getResources().getInteger(R.integer.scale_click));
        playEndHeartbeat.setRepeatMode(Animation.REVERSE);
        playEndHeartbeat.setRepeatCount(1);
    }

    /**
     * Prepara las animaciones que dan paso a la actividad Read.
     */
    private void prepareTransitionToReadAnimations() {

        //Cargamos animación al pulsar el botón de play
        clickPlay = new ScaleAnimation(1f, 1.4f, 1f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickPlay.setDuration(getResources().getInteger(R.integer.scale_click));
        clickPlay.setRepeatMode(Animation.REVERSE);
        clickPlay.setRepeatCount(1);

        clickPlay.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - clickPlay.onAnimationEnd ");

                startMergeMainButtons();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Cargamos la animación que precede al reveal que transiciona a la actividad
        preRevealPlay = new AnimationSet(false);
        ScaleAnimation preHideRevealPlay = new ScaleAnimation(1f, 1.3f, 1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        preHideRevealPlay.setDuration(getResources().getInteger(R.integer.pre_hide_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            preHideRevealPlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation hideRevealPlay = new ScaleAnimation(1.3f, 0, 1.3f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        hideRevealPlay.setDuration(getResources().getInteger(R.integer.hide_reveal_play));
        hideRevealPlay.setStartOffset(getResources().getInteger(R.integer.pre_hide_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideRevealPlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        preRevealPlay.addAnimation(preHideRevealPlay);
        preRevealPlay.addAnimation(hideRevealPlay);

        preRevealPlay.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - preRevealPlay.onAnimationEnd ");

                //play_btn.startAnimation(playTransition);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    startTransitionToReadActivity();
                } else {
                    startTransitionToReadActivityBL();
                }
            }
        });

    }

    /**
     * Prepara las animaciones que dan paso a la actividad Like.
     */
    private void prepareTransitionToLikeAnimations() {

        //Cargamos la animación que precede a la transición del botón de like y pantalla de créditos
        hideLikeForTransition = new AnimationSet(false);

        ScaleAnimation preHideLike = new ScaleAnimation(1f, 1.3f, 1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        preHideLike.setDuration(getResources().getInteger(R.integer.pre_hide_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            preHideLike.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideLike = new ScaleAnimation(1.3f, 0, 1.3f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideLike.setDuration(getResources().getInteger(R.integer.hide_standard));
        scaleHideLike.setStartOffset(getResources().getInteger(R.integer.pre_hide_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideLike.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        hideLikeForTransition.addAnimation(preHideLike);
        hideLikeForTransition.addAnimation(scaleHideLike);

        hideLikeForTransition.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hideLikeForTransition.onAnimationEnd ");

                startTransitionToLikeActivity();
            }
        });
    }

    /**
     * Prepara las animaciones que dan paso a la actividad Info.
     */
    private void prepareTransitionToInfoAnimations() {

        //Cargamos animación al pulsar el botón de vuelta al menú principal
        clickLike = new ScaleAnimation(1f, 1.4f, 1f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickLike.setDuration(getResources().getInteger(R.integer.scale_click));
        clickLike.setRepeatMode(Animation.REVERSE);
        clickLike.setRepeatCount(1);

        clickLike.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - clickLike.onAnimationEnd ");

                startTransitionToLikeActivityBL();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Cargamos animación al pulsar el botón de vuelta al menú principal
        clickInfo = new ScaleAnimation(1f, 1.4f, 1f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickInfo.setDuration(getResources().getInteger(R.integer.scale_click));
        clickInfo.setRepeatMode(Animation.REVERSE);
        clickInfo.setRepeatCount(1);

        clickInfo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - clickInfo.onAnimationEnd ");

                startTransitionToInfoActivityBL();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Cargamos la animación que precede a la transición del botón de info y pantalla de créditos
        hideInfoForReveal = new AnimationSet(false);

        ScaleAnimation preHideInfo = new ScaleAnimation(1f, 1.3f, 1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        preHideInfo.setDuration(getResources().getInteger(R.integer.pre_hide_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            preHideInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideInfo = new ScaleAnimation(1.3f, 0, 1.3f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideInfo.setDuration(getResources().getInteger(R.integer.hide_standard));
        scaleHideInfo.setStartOffset(getResources().getInteger(R.integer.pre_hide_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        hideInfoForReveal.addAnimation(preHideInfo);
        hideInfoForReveal.addAnimation(scaleHideInfo);

        hideInfoForReveal.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hideInfoForReveal.onAnimationEnd ");

                startTransitionToInfoActivity();
            }
        });
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
        showInfo = new TranslateAnimation(-getResources().getDimension(R.dimen.translate_logo_btns),
                0, 0, 0);
        showInfo.setDuration(getResources().getInteger(R.integer.show_logo_btns));
        showInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //Cargamos la animación de aparición del botón de créditos
        showLike = new TranslateAnimation(-getResources().getDimension(R.dimen.translate_logo_btns),
                0, 0, 0);
        showLike.setDuration(getResources().getInteger(R.integer.show_logo_btns));
        showLike.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showLike.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
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
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideMusic.onAnimationStart ");

                music_btn.setClickable(false);
                music_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideMusic.onAnimationEnd ");
            }
        });

        //Cargamos la animación de ocultación de botón de créditos
        hideLike = new TranslateAnimation(0,
                -getResources().getDimension(R.dimen.translate_logo_btns), 0, 0);
        hideLike.setDuration(getResources().getInteger(R.integer.hide_logo_btns));
        hideLike.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideLike.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        hideLike.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideLike.onAnimationStart ");

                like_btn.setClickable(false);
                like_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideLike.onAnimationEnd ");
            }
        });

        //Cargamos la animación de ocultación de botón de créditos
        hideInfo = new TranslateAnimation(0,
                -getResources().getDimension(R.dimen.translate_logo_btns), 0, 0);
        hideInfo.setDuration(getResources().getInteger(R.integer.hide_logo_btns));
        hideInfo.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideInfo.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        hideInfo.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideInfo.onAnimationStart ");

                info_btn.setClickable(false);
                info_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideInfo.onAnimationEnd ");
            }
        });

        //Cargamos la animación de ocultación de botón de créditos
        hideInfoReverse = new TranslateAnimation(0,
                getResources().getDimension(R.dimen.translate_logo_btns_reverse), 0, 0);
        hideInfoReverse.setDuration(getResources().getInteger(R.integer.hide_logo_btns_reverse));
        //hideInfoReverse.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideInfoReverse.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        hideInfoReverse.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideInfoReverse.onAnimationStart ");

                info_btn.setClickable(false);
                info_btn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.v(Constants.Log.CONTROLS, "MainActivity - hideInfoReverse.onAnimationEnd ");
            }
        });

        //Preparamos animaciones de la transición inicial
        colorBarOutTransition = new TranslateAnimation(0, 0, 0,
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

                Log.v(Constants.Log.CONTROLS, "MainActivity - colorBarOutTransition.onAnimationEnd ");

                switch (toActivity) {

                    case Constants.ACTIVITY_REQUEST_CODE_LIKE: {

                        Intent i = new Intent(mContext, LikeActivity.class);

                        //Pasamos la info para identificar a la actividad Like que venimos de Read
                        i.putExtra(Constants.LIKE_ACTIVITY_FROM, Constants.LIKE_ACTIVITY_FROM_MAIN);

                        startActivityForResult(i, Constants.ACTIVITY_REQUEST_CODE_LIKE);
                        break;
                    }
                    case Constants.ACTIVITY_REQUEST_CODE_INFO: {

                        Intent i = new Intent(mContext, InfoActivity.class);

                        startActivityForResult(i, Constants.ACTIVITY_REQUEST_CODE_INFO);
                        break;
                    }
                    case Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY: {

                        //Guardamos si la música está activa o no
                        MusicManager.setWasMainMusicOn(MusicManager.isMusic_on());

                        Intent i = new Intent(mContext, ReadActivityAuto.class);

                        startActivityForResult(i, Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY);

                        break;
                    }

                    case ACTIVITY_REQUEST_CODE_STANDARD: {

                        //Guardamos si la música está activa o no
                        MusicManager.setWasMainMusicOn(MusicManager.isMusic_on());

                        Intent i = new Intent(mContext, ReadActivityStandard.class);
                        //i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_STANDARD);

                        break;
                    }

                    case ACTIVITY_REQUEST_CODE_MANUAL: {

                        //Guardamos si la música está activa o no
                        MusicManager.setWasMainMusicOn(MusicManager.isMusic_on());

                        Intent i = new Intent(mContext, ReadActivityManual.class);
                        //i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_MANUAL);

                        break;
                    }
                }

                //Lo añadimos para quitar el blink, lo que hace es eliminar animaciones de transición.
                overridePendingTransition(0, 0);

                //Para Lollipop lo hacemos en startReveal
                //Indicamos que no se debe interrumpir la música en onStop
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

        logoImageOutTransitionReverse = new TranslateAnimation(
                0, getResources().getDimension(R.dimen.translate_logos), 0, 0);
        logoImageOutTransitionReverse.setDuration(getResources().getInteger(R.integer.translate_hide_logo));
        logoImageOutTransitionReverse.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoImageOutTransitionReverse.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        //Preparamos las animaciones de la transición final
        colorBarInTransition = new TranslateAnimation(0, 0,
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

                Log.v(Constants.Log.CONTROLS, "MainActivity - colorBarInTransition.onAnimationEnd ");

                startDivideMainButtons();

                //Programamos desbloqueo
                programUnlock(showAutoplayText.getDuration()+showAutoplayText.getStartOffset());
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

        logoImageInTransitionReverse = new TranslateAnimation(
                getResources().getDimension(R.dimen.translate_logos), 0, 0, 0);
        logoImageInTransitionReverse.setDuration(getResources().getInteger(R.integer.translate_show_logo));
        logoImageInTransitionReverse.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            logoImageInTransitionReverse.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        logoSubtextInTranstion.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoTextInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoImageInTransition.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
        logoImageInTransitionReverse.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));

        showMusic.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
        showInfo.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
        showLike.setStartOffset(getResources().getInteger(R.integer.delay_logo_btns));
    }

    /**
     * Prepara las animaciones de los botones de play al mezclarse o dividirse
     */
    private void prepareMergeDivideMainButtonsAnimations() {

        //Preparamos animación de mostrar texto del botón de autoplay
        showAutoplayText= new AnimationSet(true);

        AlphaAnimation alphaShowAutoplayText = new AlphaAnimation(0, 1);
        alphaShowAutoplayText.setDuration(getResources().getInteger(R.integer.show_social_text));

        ScaleAnimation scaleShowAutoplayText = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowAutoplayText.setDuration(getResources().getInteger(R.integer.show_social_text));

        TranslateAnimation translateShowAutoplayText = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn), 0);
        translateShowAutoplayText.setDuration(getResources().getInteger(R.integer.show_social_text));

        showAutoplayText.addAnimation(alphaShowAutoplayText);
        showAutoplayText.addAnimation(scaleShowAutoplayText);
        showAutoplayText.addAnimation(translateShowAutoplayText);

        showAutoplayText.setStartOffset(getResources().getInteger(R.integer.delay_show_social_text));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showAutoplayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showAutoplayText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showAutoplayText.onAnimationStart ");

                autoplayTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showAutoplayText.onAnimationEnd ");

                //Animaciones heartbeat
                startHeartBeats();
            }
        });

        //Preparamos animación de ocultar texto del botón de autoplay
        hideAutoplayText = new AnimationSet(false);
		/*
		ScaleAnimation preScaleHideAutoplayText = new ScaleAnimation(1, 1.3f, 1, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		preScaleHideAutoplayText.setDuration(getResources().getInteger(R.integer.pre_hide_like_btns));
		preScaleHideAutoplayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
				getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
		*/
        AlphaAnimation alphaHideAutoplayText = new AlphaAnimation(1, 0);
        alphaHideAutoplayText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //alphaHideAutoplayText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaHideAutoplayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideAutoplayText = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideAutoplayText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //scaleHideAutoplayText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideAutoplayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        TranslateAnimation translateHideAutoplayText = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn));
        translateHideAutoplayText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //translateHideAutoplayText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateHideAutoplayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //hideAutoplayText.addAnimation(preScaleHideAutoplayText);
        hideAutoplayText.addAnimation(alphaHideAutoplayText);
        hideAutoplayText.addAnimation(scaleHideAutoplayText);
        hideAutoplayText.addAnimation(translateHideAutoplayText);

        hideAutoplayText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hideAutoplayText.onAnimationEnd ");

                autoplayTextView.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar texto del botón de play
        showPlayText = new AnimationSet(true);

        AlphaAnimation alphaShowPlayText = new AlphaAnimation(0, 1);
        alphaShowPlayText.setDuration(getResources().getInteger(R.integer.show_social_text));

        ScaleAnimation scaleShowPlayText = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowPlayText.setDuration(getResources().getInteger(R.integer.show_social_text));

        TranslateAnimation translateShowPlayText = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn), 0);
        translateShowPlayText.setDuration(getResources().getInteger(R.integer.show_social_text));

        showPlayText.addAnimation(alphaShowPlayText);
        showPlayText.addAnimation(scaleShowPlayText);
        showPlayText.addAnimation(translateShowPlayText);

        showPlayText.setStartOffset(getResources().getInteger(R.integer.delay_show_social_text));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showPlayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showPlayText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showPlayText.onAnimationStart ");

                playTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showPlayText.onAnimationEnd ");
            }
        });

        //Preparamos animación de ocultar texto del botón de Play
        hidePlayText = new AnimationSet(false);
		/*
		ScaleAnimation preScaleHidePlayText = new ScaleAnimation(1, 1.3f, 1, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		preScaleHidePlayText.setDuration(getResources().getInteger(R.integer.pre_hide_like_btns));
		preScaleHidePlayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
				getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
		*/
        AlphaAnimation alphaHidePlayText = new AlphaAnimation(1, 0);
        alphaHidePlayText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //alphaHidePlayText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaHidePlayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHidePlayText = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHidePlayText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //scaleHidePlayText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHidePlayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        TranslateAnimation translateHidePlayText = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn));
        translateHidePlayText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //translateHidePlayText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateHidePlayText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //hidePlayText.addAnimation(preScaleHidePlayText);
        hidePlayText.addAnimation(alphaHidePlayText);
        hidePlayText.addAnimation(scaleHidePlayText);
        hidePlayText.addAnimation(translateHidePlayText);

        hidePlayText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hidePlayText.onAnimationEnd ");

                playTextView.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar texto del botón de Manual
        showManualText = new AnimationSet(true);

        AlphaAnimation alphaShowManualText = new AlphaAnimation(0, 1);
        alphaShowManualText.setDuration(getResources().getInteger(R.integer.show_social_text));

        ScaleAnimation scaleShowManualText = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShowManualText.setDuration(getResources().getInteger(R.integer.show_social_text));

        TranslateAnimation translateShowManualText = new TranslateAnimation(0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn), 0);
        translateShowManualText.setDuration(getResources().getInteger(R.integer.show_social_text));

        showManualText.addAnimation(alphaShowManualText);
        showManualText.addAnimation(scaleShowManualText);
        showManualText.addAnimation(translateShowManualText);

        showManualText.setStartOffset(getResources().getInteger(R.integer.delay_show_social_text));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showManualText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showManualText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showManualText.onAnimationStart ");

                manualTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showManualText.onAnimationEnd ");
            }
        });

        //Preparamos animación de ocultar texto del botón de Manual
        hideManualText = new AnimationSet(false);
		/*
		ScaleAnimation preScaleHideManualText = new ScaleAnimation(1, 1.3f, 1, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		preScaleHideManualText.setDuration(getResources().getInteger(R.integer.pre_hide_like_btns));
		preScaleHideManualText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
				getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
		*/
        AlphaAnimation alphaHideManualText = new AlphaAnimation(1, 0);
        alphaHideManualText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //alphaHideManualText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            alphaHideManualText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        ScaleAnimation scaleHideManualText = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideManualText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //scaleHideManualText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHideManualText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        TranslateAnimation translateHideManualText = new TranslateAnimation(0, 0, 0,
                getResources().getDimension(R.dimen.diameter_divide_btn));
        translateHideManualText.setDuration(getResources().getInteger(R.integer.hide_social_text));
        //translateHideManualText.setStartOffset(getResources().getInteger(R.integer.pre_hide_like_btns));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            translateHideManualText.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        //hideManualText.addAnimation(preScaleHideManualText);
        hideManualText.addAnimation(alphaHideManualText);
        hideManualText.addAnimation(scaleHideManualText);
        hideManualText.addAnimation(translateHideManualText);

        hideManualText.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hideManualText.onAnimationEnd ");
                manualTextView.setVisibility(View.INVISIBLE);
            }
        });

        //Preparamos animación de mostrar el botón de Autoplay
        showAutoplay = new AnimationSet(true);

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

        showAutoplay.addAnimation(alphaShowRate);
        showAutoplay.addAnimation(scaleShowRate);
        showAutoplay.addAnimation(translateShowRate);

        showAutoplay.setStartOffset(getResources().getInteger(R.integer.delay_show_social_btns));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showAutoplay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showAutoplay.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showAutoplay.onAnimationStart ");

                autoplay_btn.setVisibility(View.VISIBLE);
                autoplay_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showAutoplay.onAnimationEnd ");
            }
        });

        //Preparamos animación de ocultar el botón de Autoplay
        hideAutoplay = new AnimationSet(true);

        ScaleAnimation scaleAutoplayHide = new ScaleAnimation(1, 0f, 1, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAutoplayHide.setDuration(getResources().getInteger(R.integer.hide_social_btns));

        TranslateAnimation translateAutoplayHide = new TranslateAnimation(0,
                getResources().getDimension(R.dimen.margin_horizontal_divide_btn), 0, 0);
        translateAutoplayHide.setDuration(getResources().getInteger(R.integer.hide_social_btns));
        translateAutoplayHide.setFillAfter(true);

        hideAutoplay.setStartOffset(getResources().getInteger(R.integer.delay_hide_social_btns));

        hideAutoplay.addAnimation(scaleAutoplayHide);
        hideAutoplay.addAnimation(translateAutoplayHide);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideAutoplay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideAutoplay.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hideAutoplay.onAnimationEnd ");

                autoplay_btn.setClickable(false);
                autoplay_btn.setVisibility(View.INVISIBLE);
            }
        });

        showPlay =  new AnimationSet(true);

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

                Log.v(Constants.Log.CONTROLS, "MainActivity - showPlay.onAnimationStart ");

                play_btn.setVisibility(View.VISIBLE);
                play_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showPlay.onAnimationEnd ");
            }
        });

        hidePlay = new AnimationSet(false);

        ScaleAnimation scaleHidePlay = new ScaleAnimation(1, 0, 1f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHidePlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scaleHidePlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }

        RotateAnimation rotatePlayPlay = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rotatePlayPlay.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        }
        rotatePlayPlay.setDuration(getResources().getInteger(R.integer.change_icon_standard));

        hidePlay.setStartOffset(getResources().getInteger(R.integer.delay_hide_play_btn));

        hidePlay.addAnimation(rotatePlayPlay);
        hidePlay.addAnimation(scaleHidePlay);

        hidePlay.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hidePlay.onAnimationStart ");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hidePlay.onAnimationEnd ");

                play_btn.setClickable(false);
                play_btn.setVisibility(View.INVISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    startTransitionToReadActivity();
                } else {
                    startTransitionToReadActivityBL();
                }
            }
        });

        //Preparamos animación de mostrar el botón de donate
        showManual = new AnimationSet(true);

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

        showManual.addAnimation(alphaShowDonate);
        showManual.addAnimation(scaleDonateShow);
        showManual.addAnimation(translateDonateShow);

        showManual.setStartOffset(getResources().getInteger(R.integer.delay_show_social_btns));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            showManual.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        showManual.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showManual.onAnimationStart ");

                manual_btn.setVisibility(View.VISIBLE);
                manual_btn.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - showManual.onAnimationEnd ");
            }
        });

        //Preparamos animación de ocultar el botón de Manual
        hideManual = new AnimationSet(true);

        ScaleAnimation scaleHideManual = new ScaleAnimation(1, 0f, 1, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleHideManual.setDuration(getResources().getInteger(R.integer.hide_social_btns));

        TranslateAnimation translateHideManual = new TranslateAnimation(0,
                -getResources().getDimension(R.dimen.margin_horizontal_divide_btn), 0, 0);
        translateHideManual.setDuration(getResources().getInteger(R.integer.hide_social_btns));
        translateHideManual.setFillAfter(true);

        hideManual.setStartOffset(getResources().getInteger(R.integer.delay_hide_social_btns));

        hideManual.addAnimation(scaleHideManual);
        hideManual.addAnimation(translateHideManual);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            hideManual.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        hideManual.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.CONTROLS, "MainActivity - hideManual.onAnimationEnd ");

                manual_btn.setClickable(false);
                manual_btn.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * Prepara la transición final del botón de play al volver de la actividad de lectura
     */
    private void preparePlayTransition() {

        //Junta el desplazamiento del botón de play como la restauración de su tamaño después del PreReveal
        playTransition = new AnimationSet(true);

        ScaleAnimation showPlayForReveal = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        showPlayForReveal.setDuration(getResources().getInteger(R.integer.show_standard));

        TranslateAnimation transition = new TranslateAnimation(0, 0, 0, -desplazamientoPlay);
        transition.setDuration(getResources().getInteger(R.integer.translate_play));
        transition.setStartOffset(getResources().getInteger(R.integer.delay_play_transition));

        playTransition.setFillAfter(true);
        playTransition.addAnimation(showPlayForReveal);
        playTransition.addAnimation(transition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            playTransition.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }
        playTransition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.METHOD, "MainActivity - playTransition.onAnimationStart ");
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.METHOD, "MainActivity - playTransition.onAnimationEnd ");

                //Fix absurdo. Evita flick en el botón al cambiar su posición con setLayoutParams dentro de onAnimationEnd
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                play_btn.startAnimation(animation);

                setPlayButtonToSmallSize();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Junta el desplazamiento del botón de play como la restauración de su tamaño después del PreReveal
        playTransitionReturn = new AnimationSet(true);

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

        playTransitionReturn.addAnimation(adjustPlayTransition);
        playTransitionReturn.addAnimation(transitionReturn);

        playTransitionReturn.setFillAfter(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            playTransitionReturn.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                    getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        }

        playTransitionReturn.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Log.v(Constants.Log.METHOD, "MainActivity - playTransitionReturn.onAnimationStart ");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    play_btn.setElevation(getResources().getDimension(R.dimen.button_higher_elevation));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Log.v(Constants.Log.METHOD, "MainActivity - playTransitionReturn.onAnimationEnd ");

                //Fix absurdo. Evita flick en el botón al cambiar su posición con setLayoutParams dentro de onAnimationEnd
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                play_btn.startAnimation(animation);

                setPlayButtonToBigSize();

                //play_btn.clearAnimation();
                play_btn.startAnimation(heartBeatPlay);
            }
        });

        playTransitionReturn.setStartOffset(getResources().getInteger(R.integer.delay_show_logo));
    }

    private void startMergeMainButtons() {

        Log.v(Constants.Log.METHOD, "MainActivity - startMergeMainButtons ");

        //Escondemos texto
        autoplayTextView.startAnimation(hideAutoplayText);
        playTextView.startAnimation(hidePlayText);
        manualTextView.startAnimation(hideManualText);
        //Los botones con su delay
        autoplay_btn.startAnimation(hideAutoplay);
        play_btn.startAnimation(hidePlay);
        manual_btn.startAnimation(hideManual);
    }

    private void startDivideMainButtons() {

        Log.v(Constants.Log.METHOD, "MainActivity - startDivideMainButtons ");

        //Mostramos texto
        autoplayTextView.startAnimation(showAutoplayText);
        playTextView.startAnimation(showPlayText);
        manualTextView.startAnimation(showManualText);
        //Los botones con su delay
        autoplay_btn.startAnimation(showAutoplay);
        //Lo hacemos antes de que termine el reveal
        //play_btn.startAnimation(showPlay);
        manual_btn.startAnimation(showManual);
    }

    /**
     * Animaciones que dan paso a la actividad Read
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startTransitionToReadActivity() {

        Log.v(Constants.Log.METHOD, "MainActivity - startTransitionToReadActivity ");

        //colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayout.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);
        music_btn.startAnimation(hideMusic);
        like_btn.startAnimation(hideLike);
        info_btn.startAnimation(hideInfo);
        startRevealPlay();
    }

    /**
     * Animaciones que dan paso a la actividad Read
     */
    private void startTransitionToReadActivityBL() {

        Log.v(Constants.Log.METHOD, "MainActivity - startTransitionToReadActivityBL ");

        colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayout.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);
        music_btn.startAnimation(hideMusic);
        like_btn.startAnimation(hideLike);
        info_btn.startAnimation(hideInfo);
    }

    /**
     * Animaciones que dan paso a la actividad Like
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startTransitionToLikeActivity() {

        Log.v(Constants.Log.METHOD, "MainActivity - startTransitionToLikeActivity ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.pink_dark, null));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.pink_dark));
        }

        logoTextLayout.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransitionReverse);
        play_btn.startAnimation(playEndHeartbeat);
        music_btn.startAnimation(hideMusic);
        info_btn.startAnimation(hideInfoReverse);

        startRevealLike();
    }

    /**
     * Animaciones que dan paso a la actividad Like antes de Lollipop
     */
    private void startTransitionToLikeActivityBL() {

        Log.v(Constants.Log.METHOD, "MainActivity - startTransitionToLikeActivityBL ");

        colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayout.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);
        play_btn.startAnimation(playEndHeartbeat);
        music_btn.startAnimation(hideMusic);
        like_btn.startAnimation(hideLike);
        info_btn.startAnimation(hideInfo);
    }

    /**
     * Animaciones que dan paso a la actividad Info
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startTransitionToInfoActivity() {

        Log.v(Constants.Log.METHOD, "MainActivity - startTransitionToInfoActivity ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_dark, null));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_dark));
        }

        //colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayout.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransition);
        play_btn.startAnimation(playEndHeartbeat);
        music_btn.startAnimation(hideMusic);
        like_btn.startAnimation(hideLike);
        startRevealInfo();
    }

    /**
     * Animaciones que dan paso a la actividad Info
     */
    private void startTransitionToInfoActivityBL() {

        Log.v(Constants.Log.METHOD, "MainActivity - startTransitionToInfoActivityBL ");

        colorBarLayout.startAnimation(colorBarOutTransition);
        logoTextLayout.startAnimation(logoTextOutTransition);
        logoSubTextView.startAnimation(logoSubtextOutTransition);
        logoImage.startAnimation(logoImageOutTransitionReverse);
        play_btn.startAnimation(playEndHeartbeat);
        music_btn.startAnimation(hideMusic);
        like_btn.startAnimation(hideLike);
        info_btn.startAnimation(hideInfo);
    }

    /**
     * Pone en marcha la animación de retorno a la actividad principal desde la actividad Read
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startShowLayoutFromRead() {

        Log.v(Constants.Log.METHOD, "MainActivity - startShowLayoutFromRead ");

        //play_btn.startAnimation(playTransitionReturn);
        //colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayout.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransition);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        like_btn.startAnimation(showLike);
        like_btn.setVisibility(View.VISIBLE);
        like_btn.setClickable(true);
        info_btn.startAnimation(showInfo);
        info_btn.setVisibility(View.VISIBLE);
        info_btn.setClickable(true);
        startUnvealPlayReturn();
        play_btn.startAnimation(showPlay);
    }

    /**
     * Pone en marcha la animación de retorno a la actividad principal desde la actividad Read
     */
    private void startShowLayoutFromReadBL() {

        Log.v(Constants.Log.METHOD, "MainActivity - startShowLayoutFromReadBL ");

        //play_btn.startAnimation(playTransitionReturn);
        colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayout.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransition);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        like_btn.startAnimation(showLike);
        like_btn.setVisibility(View.VISIBLE);
        like_btn.setClickable(true);
        info_btn.startAnimation(showInfo);
        info_btn.setVisibility(View.VISIBLE);
        info_btn.setClickable(true);
        play_btn.startAnimation(showPlay);
    }

    /**
     * Pone en marcha la animación de retorno a la actividad principal desde la actividad Like
     */
    private void startShowLayoutFromLike() {

        Log.v(Constants.Log.METHOD, "MainActivity - startShowLayoutFromLike ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark, null));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark));
        }

        //play_btn.startAnimation(playTransitionReturn);
        logoTextLayout.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransitionReverse);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        info_btn.startAnimation(showInfo);
        info_btn.setVisibility(View.VISIBLE);
        info_btn.setClickable(true);
        //Sin animación
        like_btn.setVisibility(View.VISIBLE);
        like_btn.setClickable(true);
        play_btn.setVisibility(View.VISIBLE);
        play_btn.setClickable(true);

        startUnvealLikeReturn();
    }

    /**
     * Pone en marcha la animación de retorno a la actividad principal desde la actividad Like antes de Lollipop
     */
    private void startShowLayoutFromLikeBL() {

        Log.v(Constants.Log.METHOD, "MainActivity - startShowLayoutFromLikeBL ");

        colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayout.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransitionReverse);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        info_btn.startAnimation(showInfo);
        info_btn.setVisibility(View.VISIBLE);
        info_btn.setClickable(true);
        like_btn.startAnimation(showLike);
        like_btn.setVisibility(View.VISIBLE);
        like_btn.setClickable(true);
        //Sin animación. Al venir de Read play es invisible.
        play_btn.setVisibility(View.VISIBLE);
        play_btn.setClickable(true);
    }

    /**
     * Pone en marcha la animación de retorno a la actividad principal desde la actividad Info
     */
    private void startShowLayoutFromInfo() {

        Log.v(Constants.Log.METHOD, "MainActivity - startShowLayoutFromInfo ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark, null));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_primary_dark));
        }

        //play_btn.startAnimation(playTransitionReturn);
        //colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayout.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransitionReverse);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        info_btn.setVisibility(View.VISIBLE);
        info_btn.setClickable(true);
        like_btn.startAnimation(showLike);
        like_btn.setVisibility(View.VISIBLE);
        like_btn.setClickable(true);
        startUnvealInfoReturn();
    }

    /**
     * Pone en marcha la animación de retorno a la actividad principal desde la actividad Info
     */
    private void startShowLayoutFromInfoBL() {

        Log.v(Constants.Log.METHOD, "MainActivity - startShowLayoutFromInfoBL ");

        //play_btn.startAnimation(playTransitionReturn);
        colorBarLayout.startAnimation(colorBarInTransition);
        logoTextLayout.startAnimation(logoTextInTransition);
        logoSubTextView.startAnimation(logoSubtextInTranstion);
        logoImage.startAnimation(logoImageInTransitionReverse);
        music_btn.startAnimation(showMusic);
        music_btn.setVisibility(View.VISIBLE);
        music_btn.setClickable(true);
        info_btn.startAnimation(showInfo);
        info_btn.setVisibility(View.VISIBLE);
        info_btn.setClickable(true);
        like_btn.startAnimation(showLike);
        like_btn.setVisibility(View.VISIBLE);
        like_btn.setClickable(true);
    }

    /**
     * Pone en marcha animación Reveal que da inicio a la actividad principal
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startRevealPlay() {

        Log.v(Constants.Log.METHOD, "MainActivity - startRevealPlay ");

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
        anim.setDuration(getResources().getInteger(R.integer.reveal_standard));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startRevealPlay.onAnimationStart ");

                //Hacemos visible el reveal y le cambiamos el color
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.color_accent, null));
                } else {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.color_accent));
                }
                revealLayout.setVisibility(View.VISIBLE);
                play_btn.setElevation(0);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startRevealPlay.onAnimationEnd ");

                //Guardamos si la música está activa o no
                MusicManager.setWasMainMusicOn(MusicManager.isMusic_on());

                switch (toActivity) {

                    case Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY: {

                        /*
                        //Colocamos btn_play si se hubiera interrumpido la animación playTransition
                        if (!playTransition.hasEnded()) {

                            Log.w(Constants.Log.METHOD, "MainActivity - startRevealPlay.playTransition.notEnded ");

                            playTransition.cancel();
                        }
                        */

                        Intent i = new Intent(mContext, ReadActivityAuto.class);
                        //i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                (Activity) mContext, play_btn, "play");
                        startActivityForResult(i, Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY);
                        //Lo añadimos para quitar el blink, lo que hace es eliminar animaciones de transición.
                        overridePendingTransition(0, 0);

                        break;
                    }

                    case ACTIVITY_REQUEST_CODE_STANDARD: {

                        Intent i = new Intent(mContext, ReadActivityStandard.class);
                        //i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_STANDARD);
                        //Lo añadimos para quitar el blink, lo que hace es eliminar animaciones de transición.
                        overridePendingTransition(0, 0);

                        break;
                    }

                    case ACTIVITY_REQUEST_CODE_MANUAL: {

                        Intent i = new Intent(mContext, ReadActivityManual.class);
                        //i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                        startActivityForResult(i, ACTIVITY_REQUEST_CODE_MANUAL);
                        //Lo añadimos para quitar el blink, lo que hace es eliminar animaciones de transición.
                        overridePendingTransition(0, 0);

                        break;
                    }

                }

                //Indicamos que no se debe interrumpir la música en onStop
                continue_music = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        anim.start();
    }

    /**
     * Pone en marcha laanimación Reveal de retorno de la actividad principal.
     */

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUnvealPlayReturn() {

        Log.v(Constants.Log.METHOD, "MainActivity - startUnvealPlayReturn ");

        //Calculamos el centro de la animación
        int cx = (mainLayout.getLeft() + mainLayout.getRight()) / 2;
        int cy = colorBarLayout.getTop();

        //Calculamos el radio de la animación
        int initialRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, initialRadius, 0);
        anim.setDuration(getResources().getInteger(R.integer.unveal_long));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealPlayReturn.onAnimationStart ");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealPlayReturn.onAnimationEnd ");

                revealLayout.setVisibility(View.INVISIBLE);

                MusicManager.startResumeMusic();

                //Restauramos la elevación
                play_btn.setElevation(getResources().getDimension(R.dimen.button_higher_elevation));

                startDivideMainButtons();

                //Programamos desbloqueo
                programUnlock(showAutoplayText.getDuration()+showAutoplayText.getStartOffset());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        //anim.setStartDelay(getResources().getInteger(R.integer.delay_show_logo));
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUnvealInicio() {

        Log.v(Constants.Log.METHOD, "MainActivity - startUnvealInicio ");

        //Calculamos el centro de la animación
        int cx = (mainLayout.getLeft() + mainLayout.getRight()) / 2;
        int cy = colorBarLayout.getTop();

        //Calculamos el radio de la animación
        int initialRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, initialRadius, 0);
        anim.setDuration(getResources().getInteger(R.integer.unveal_long));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealInicio.onAnimationStart ");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealInicio.onAnimationEnd ");

                revealLayout.setVisibility(View.INVISIBLE);

                //Restauramos la elevación
                play_btn.setElevation(getResources().getDimension(R.dimen.button_higher_elevation));

                startDivideMainButtons();

                //Programamos desbloqueo
                programUnlock(showAutoplayText.getDuration()+showAutoplayText.getStartOffset());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        //anim.setStartDelay(getResources().getInteger(R.integer.delay_show_logo));
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        anim.start();
    }

    /**
     * Pone en marcha animación Reveal que da inicio a la actividad Like
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startRevealLike() {

        Log.v(Constants.Log.METHOD, "MainActivity - startRevealLike ");

        //Calculamos el centro de la animación
        int cx = (like_btn.getLeft() + like_btn.getRight()) / 2;
        int cy = (like_btn.getTop() + like_btn.getBottom()) / 2;

        //Calculamos el radio de la animación
        int finalRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, 0, finalRadius);
        anim.setDuration(getResources().getInteger(R.integer.reveal_standard));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startRevealLike.onAnimationStart ");

                //Hacemos visible el reveal y le cambiamos el color
                revealLayout.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.pink, null));
                } else {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.pink));
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startRevealLike.onAnimationEnd ");


                Intent i = new Intent(mContext, LikeActivity.class);

                //Pasamos la info para identificar a la actividad Like que venimos de Read
                i.putExtra(Constants.LIKE_ACTIVITY_FROM, Constants.LIKE_ACTIVITY_FROM_MAIN);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) mContext, play_btn, "play");

                startActivityForResult(i, Constants.ACTIVITY_REQUEST_CODE_LIKE);
                //Lo añadimos para quitar el blink, lo que hace es eliminar animaciones de transición.
                overridePendingTransition(0, 0);
                //Indicamos que no se debe interrumpir la música en onStop
                continue_music = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        anim.start();
    }


    /**
     * Pone en marcha animación Reveal que da inicio a la actividad Info
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startRevealInfo() {

        Log.v(Constants.Log.METHOD, "MainActivity - startRevealInfo ");

        //Calculamos el centro de la animación
        int cx = (info_btn.getLeft() + info_btn.getRight()) / 2;
        int cy = (info_btn.getTop() + info_btn.getBottom()) / 2;

        //Calculamos el radio de la animación
        int finalRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, 0, finalRadius);
        anim.setDuration(getResources().getInteger(R.integer.reveal_standard));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startRevealInfo.onAnimationStart ");

                //Hacemos visible el reveal y le cambiamos el color
                revealLayout.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.purple, null));
                } else {
                    revealLayout.setBackgroundColor(getResources().getColor(R.color.purple));
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startRevealInfo.onAnimationEnd ");

                Intent i = new Intent(mContext, InfoActivity.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) mContext, play_btn, "play");

                startActivityForResult(i, Constants.ACTIVITY_REQUEST_CODE_INFO);
                //Lo añadimos para quitar el blink, lo que hace es eliminar animaciones de transición.
                overridePendingTransition(0, 0);

                //Indicamos que no se debe interrumpir la música en onStop
                continue_music = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_linear_in)));
        anim.start();
    }

    /**
     * Pone en marcha animación Reveal de retorno de la actividad de Like
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUnvealLikeReturn() {

        Log.v(Constants.Log.METHOD, "MainActivity - startUnvealLikeReturn ");

        //Calculamos el centro de la animación
        int cx = (like_btn.getLeft() + like_btn.getRight()) / 2;
        int cy = (like_btn.getTop() + like_btn.getBottom()) / 2;

        //Calculamos el radio de la animación
        int initialRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, initialRadius,
                        getResources().getDimension(R.dimen.diameter_logo_btns) / 2);
        anim.setDuration(getResources().getInteger(R.integer.unveal_standard));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealLikeReturn.onAnimationStart ");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealLikeReturn.onAnimationEnd ");

                revealLayout.setVisibility(View.INVISIBLE);

                startDivideMainButtons();

                //Programamos desbloqueo
                programUnlock(showAutoplayText.getDuration()+showAutoplayText.getStartOffset());
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
     * Pone en marcha animación Reveal de retorno de la actividad de Info
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUnvealInfoReturn() {

        Log.v(Constants.Log.METHOD, "MainActivity - startUnvealInfoReturn ");

        //Calculamos el centro de la animación
        int cx = (info_btn.getLeft() + info_btn.getRight()) / 2;
        int cy = (info_btn.getTop() + info_btn.getBottom()) / 2;

        //Calculamos el radio de la animación
        int initialRadius = (int) Math.sqrt(Math.pow(revealLayout.getWidth(), 2) +
                Math.pow(revealLayout.getHeight(), 2));

        //Creamos la animación
        Animator anim =
                ViewAnimationUtils.createCircularReveal(revealLayout,
                        cx, cy, initialRadius,
                        getResources().getDimension(R.dimen.diameter_logo_btns) / 2);
        anim.setDuration(getResources().getInteger(R.integer.unveal_standard));

        //Hacemos visible la vista y empezamos la animación
        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealInfoReturn.onAnimationStart ");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.v(Constants.Log.METHOD, "MainActivity - startUnvealInfoReturn.onAnimationEnd ");

                revealLayout.setVisibility(View.INVISIBLE);

                startDivideMainButtons();

                //Programamos desbloqueo
                programUnlock(showAutoplayText.getDuration()+showAutoplayText.getStartOffset());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        //anim.setStartDelay(getResources().getInteger(R.integer.delay_reveal_info));
        anim.setInterpolator((PathInterpolator) (AnimationUtils.loadInterpolator(
                getApplicationContext(), android.R.interpolator.fast_out_slow_in)));
        anim.start();
    }

    /**
     * Preparamos el botón de play para lanzar la parte final de la animación de retorno del reveal.
     *
     * @see Activity#onActivityResult(int, int, Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.v(Constants.Log.METHOD, "MainActivity - onActivityResult ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            revealLayout.setBackgroundColor(getResources().getColor(R.color.color_primary, null));
        } else {
            revealLayout.setBackgroundColor(getResources().getColor(R.color.color_primary));
        }

        clearAnimations();

        if (requestCode == Constants.ACTIVITY_REQUEST_CODE_AUTOPLAY ||
                requestCode == Constants.ACTIVITY_REQUEST_CODE_STANDARD ||
                requestCode == Constants.ACTIVITY_REQUEST_CODE_MANUAL) {

            Log.v(Constants.Log.METHOD, "MainActivity - onActivityResult FromRead ");

            //Si venimos de Read o hemos saltado a Like
            if (resultCode == RESULT_OK) {

                Log.v(Constants.Log.METHOD, "MainActivity - onActivityResult FromRead - Real ");
                //Preparamos música
                //Restauramos valor por defecto para que se pare la música en onStop
                continue_music = false;
                MusicManager.createMusic(true);
                //Recordar el estado del volumen
                if (MusicManager.wasMainMusicOn()) {

                    MusicManager.setMusicVolumeOn();
                    MusicManager.setMusic_on(true);
                } else {

                    MusicManager.setMusicVolumeOff();
                    MusicManager.setMusic_on(false);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    startShowLayoutFromRead();
                } else {
                    startShowLayoutFromReadBL();
                }
            }
            //Venimos de like en realidad
            else {

                Log.v(Constants.Log.METHOD, "MainActivity - onActivityResult FromRead - Like ");
                //Ajustamos el aspecto del botón music
                if (MusicManager.isMusic_on()) {

                    setMusicIconOn();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn, null));
                    } else {
                        music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off));
                    }
                } else {

                    setMusicIconOff();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_disabled, null));
                    } else {
                        music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on));
                    }
                }
                //Restauramos valor por defecto para que se pare la música en onStop
                continue_music = false;

                setPlayButtonToBigSize();

                //Restauramos la elevación
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    play_btn.setElevation(getResources().getDimension(R.dimen.button_higher_elevation));

                    startShowLayoutFromLike();
                } else {

                    startShowLayoutFromLikeBL();
                }
            }
        }

        if (requestCode == Constants.ACTIVITY_REQUEST_CODE_LIKE) {

            Log.v(Constants.Log.METHOD, "MainActivity - onActivityResult FromLike ");

            //Ajustamos el aspecto del botón music
            if (MusicManager.isMusic_on()) {

                setMusicIconOn();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn, null));
                } else {
                    music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off));
                }
            } else {

                setMusicIconOff();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_disabled, null));
                } else {
                    music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on));
                }
            }
            //Restauramos valor por defecto para que se pare la música en onStop
            continue_music = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                startShowLayoutFromLike();
            } else {
                startShowLayoutFromLikeBL();
            }
        }

        if (requestCode == Constants.ACTIVITY_REQUEST_CODE_INFO) {

            Log.v(Constants.Log.METHOD, "MainActivity - onActivityResult FromInfo ");

            //Ajustamos el aspecto del botón music
            if (MusicManager.isMusic_on()) {

                setMusicIconOn();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn, null));
                } else {
                    music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_on_off));
                }
            } else {

                setMusicIconOff();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    music_btn.setBackground(getResources().getDrawable(R.drawable.ripple_toolbar_btn_disabled, null));
                } else {
                    music_btn.setBackground(getResources().getDrawable(R.drawable.transition_toolbar_icon_off_on));
                }

            }
            //Restauramos valor por defecto para que se pare la música en onStop
            continue_music = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                startShowLayoutFromInfo();
            } else {
                startShowLayoutFromInfoBL();
            }
        }
    }

    /**
     * Ajusta el botón de play al tamaño grande
     */
    private void setPlayButtonToBigSize() {

        Log.v(Constants.Log.METHOD, "MainActivity - setPlayButtonToBigSize ");

        //Recolocamos el botón de play, ya que se queda lo modificamos al pasar a Read
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) play_btn.getLayoutParams();
        params.width = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
        params.height = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn_big);
        params.gravity = Gravity.CENTER | Gravity.BOTTOM;
        params.bottomMargin = (int) getResources().getDimension(R.dimen.margin_play_btn_big);

        play_btn.setLayoutParams(params);

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

            play_btn.setOutlineProvider(outline_play);
            play_btn.setClipToOutline(true);
        }
    }

    /**
     * Ajusta el botón de play al tamaño pequeño
     */
    private void setPlayButtonToSmallSize() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) play_btn.getLayoutParams();
        params.width = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn);
        params.height = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn);
        params.gravity = Gravity.CENTER;
        params.bottomMargin = 0;

        play_btn.setLayoutParams(params);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ViewOutlineProvider outline_play = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        int diameter = getResources().getDimensionPixelSize(R.dimen.diameter_play_btn);
                        outline.setOval(0, 0, diameter, diameter);
                    }
                }
            };

            play_btn.setOutlineProvider(outline_play);
            play_btn.setClipToOutline(true);
        }

    }

    /**
     * Pone el icono de música de la barra de controles según densidad de pixel.
     */
    private void setMusicIconOn() {

        Log.v(Constants.Log.METHOD, "MainActivity - setMusicIconOn ");

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
        }else {
            music_btn.setImageResource(R.drawable.ic_music_note_white_18dp);
        }
    }

    /**
     * Pone el icono de música de la barra de controles según densidad de pixel.
     */
    private void setMusicIconOff() {

        Log.v(Constants.Log.METHOD, "MainActivity - setMusicIconOff ");

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
        }else {
            music_btn.setImageResource(R.drawable.ic_music_note_off_white_18dp);
        }
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {

        Log.w(Constants.Log.METHOD, "MainActivity - hideSystemUI ");

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
     *
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
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    /**
     * Controlamos con el singletap esconder las barras de status y nav
     *
     * @author quayo
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            boolean visible = (mDecorView.getSystemUiVisibility()
                    & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;

            if (visible) {

                hideSystemUI();
            }

            return super.onSingleTapUp(e);
        }
    }

    /**
     * Bloquea el botón de play
     */
    public void lockButtons() {

        btn_locked = true;

        Log.i(Constants.Log.METHOD, "MainActivity - lockButtons = "+btn_locked);
    }

    /**
     * Desbloquea el botón de play
     */
    public void unlockButtons() {

        btn_locked = false;
        
        Log.i(Constants.Log.METHOD, "MainActivity - unlockButtons = "+btn_locked);
    }

    /**
     * Comprueba si el botón está bloqueado o no
     *
     * @return
     */
    public boolean areButtonsLocked() {

        Log.w(Constants.Log.METHOD, "Main Activity areButtonsLocked = " + btn_locked);

        return btn_locked;
    }

    /**
     * Programa desbloqueo dentro de "duration" ms.
     * @param duration
     */
    protected void programUnlock(long duration) {

        Log.i(Constants.Log.METHOD, "MainActivity - programUnlock: "+duration);

        //Programamos desbloqueo
        new Handler().postDelayed(new Runnable() {
            public void run() {

                unlockButtons();
            }
        }, duration);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    protected void onResume() {
        Log.v(Constants.Log.METHOD, "MainActivity - onResume ");

        //Escondemos barras
        hideSystemUI();

        MusicManager.startResumeMusic();

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(Constants.Log.METHOD, "MainActivity - onPause ");

        //Pausamos música
        if (!continue_music) MusicManager.pauseMusic();

        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.v(Constants.Log.METHOD, "MainActivity - onRestart ");

        MusicManager.createMusicRestart(true);

        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.v(Constants.Log.METHOD, "MainActivity - onStop ");

        //Aquí controlamos si el onStop se produce al pasar a otra actividad o al salir de la app
        if (!continue_music) MusicManager.stopMusic();

        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        Log.v(Constants.Log.METHOD, "MainActivity - onDestroy ");

        super.onDestroy();
    }

}