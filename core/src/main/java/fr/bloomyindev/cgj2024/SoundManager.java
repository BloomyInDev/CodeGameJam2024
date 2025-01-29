package fr.bloomyindev.cgj2024;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private static float maxVolume = 1f;
    private static Music music = Gdx.audio.newMusic(Gdx.files.internal("eq9nomel-16.wav"));
    private static Music musicWMel = Gdx.audio.newMusic(Gdx.files.internal("eq9mel-16.wav"));
    private static Music musicTroll = Gdx.audio.newMusic(Gdx.files.internal("ACLong.mp3"));
    private AudioDevice audio;

    public SoundManager(AudioDevice audio) {
        this.audio = audio;
        music.setVolume(.5f);
        musicWMel.setVolume(0f);
        musicTroll.setVolume(0f);
        musicWMel.setLooping(true);
        music.setLooping(true);
        musicTroll.setLooping(true);
        music.play();
        musicWMel.play();
        musicTroll.play();
    }

    public void playSound(long distance, Star star) {
        if (star.isVisitable() && distance <= 5000) {
            float vol = calculateVolumeWithDistance(distance);
            System.out.printf("%f (dist %d) playing\n", vol, distance);
            music.setVolume(0);
            musicWMel.setVolume(vol);
            musicTroll.setVolume(0f);
        } else if (star.isCholletStar() && distance <= 5000) {
            float vol = calculateVolumeWithDistance(distance);
            musicTroll.setVolume(vol);
            music.setVolume(0f);
        } else if (!star.isVisitable() && !star.isCholletStar() && distance <= 5000) {
            float vol = calculateVolumeWithDistance(distance);
            music.setVolume(0);
            if (distance <= 300) {
                if (distance % 2 == 0) {
                    musicWMel.setVolume(vol);
                } else {
                    musicWMel.setVolume(1 - vol);
                }
            } else {
                musicWMel.setVolume(vol);
            }
            musicTroll.setVolume(0f);
        } else {
            music.setVolume(0.5f);
            musicTroll.setVolume(0f);
            musicWMel.setVolume(0f);
        }

    }

    private float calculateVolumeWithDistance(long distance) {
        return (float) Math.exp(-((double) distance / 1000));
    }
}
