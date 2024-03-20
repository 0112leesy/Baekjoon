import java.util.*;

class Solution {

    static class Music {
        ArrayList<Note> melody;
        int duration;
        String name;

        Music(ArrayList<Note> melody, int duration, String name) {
            this.melody = melody;
            this.duration = duration;
            this.name = name;
        }
    }

    static class Note {
        String note;

        Note(String note) {
            this.note = note;
        }

        boolean isEqualTo(Note n) {
            return this.note.equals(n.note);
        }

        @Override
        public String toString() {
            return note;
        }
    }

    static ArrayList<Note> searchingMusic;

    public String solution(String m, String[] musicinfos) {
        searchingMusic = convert(m);
        ArrayList<Music> musics = new ArrayList<>();

        for(int i=0; i<musicinfos.length; i++) {
            String[] infos = musicinfos[i].split(",");
            int duration = getDuration(infos[0], infos[1]);
            String name = infos[2];
            ArrayList<Note> melody = convert(infos[3]);
            musics.add(new Music(melody, duration, name));
        }

        Collections.sort(musics, (m1, m2) -> m2.duration - m1.duration);

        String answer = "(None)";
        for(int i=0; i<musics.size(); i++) {
            if(inspectMusic(musics.get(i))) {
                answer = musics.get(i).name;
                break;
            }
        }

        return answer;
    }

    static ArrayList<Note> convert(String music) {
        ArrayList<Note> converted = new ArrayList<>();

        for(int i=0; i<music.length(); i++) {
            String str = Character.toString(music.charAt(i));
            if(i != music.length()-1 && music.charAt(i+1) == '#') {
                str += Character.toString(music.charAt(i+1));
                i += 1;
            }
            converted.add(new Note(str));
        }
        return converted;
    }

    static boolean inspectMusic(Music music) {
        int cnt = 0;
        int idx = 0;
        ArrayList<Note> musicWindow = new ArrayList<>();

        while(cnt <= music.duration) {
            if(musicWindow.size() == searchingMusic.size()) {
                musicWindow.remove(0);
            }
            musicWindow.add(music.melody.get(idx));

            if(musicWindow.size() == searchingMusic.size()) {
                boolean flag = true;
                for(int i=0; i<musicWindow.size(); i++) {
                    if(!musicWindow.get(i).isEqualTo(searchingMusic.get(i))) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    return true;
                }
            }

            idx += 1;
            if(idx == music.melody.size()) {
                idx = 0;
            }
            cnt += 1;
        }
        return false;
    }

    static int getDuration(String startTime, String endTime) {
        String[] startInfo = startTime.split(":");
        String[] endInfo = endTime.split(":");

        int startHour = Integer.parseInt(startInfo[0]);
        int startMinute = Integer.parseInt(startInfo[1]);

        int endHour = Integer.parseInt(endInfo[0]);
        int endMinute = Integer.parseInt(endInfo[1]);

        return asMinutes(endHour, endMinute) - asMinutes(startHour, startMinute);
    }

    static int asMinutes(int hour, int minute) {
        return hour * 60 + minute;
    }
}