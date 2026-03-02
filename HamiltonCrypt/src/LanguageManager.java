import java.util.HashMap;
import java.util.Map;

/**
 * Utility for managing UI strings in multiple languages (English and Hebrew).
 */
public class LanguageManager {
    public enum Language { EN, HE }
    private static Language currentLang = Language.HE;
    private static final Map<String, String[]> strings = new HashMap<>();

    static {
        // Syntax: { English, Hebrew }
        strings.put("window_title", new String[]{"Hamiltonian Cryptography", "קריפטוגרפיה המילטונית"});
        strings.put("btn_encrypt", new String[]{"Encrypt File", "הצפן קובץ"});
        strings.put("btn_decrypt", new String[]{"Decrypt File", "פענח קובץ"});
        strings.put("btn_reset", new String[]{"Reset Path", "נקה מסלול"});
        strings.put("btn_hint", new String[]{"Get Hint", "רמז / פתרון"});

        // Status updates
        strings.put("status_start", new String[]{"Status: Please draw a Hamiltonian path on the graph", "סטטוס: נא לצייר מסלול המילטוני על הגרף"});
        strings.put("status_path_complete", new String[]{"Status: Hamiltonian path created! Key is ready.", "סטטוס: נוצר מסלול המילטוני! המפתח מוכן."});
        strings.put("status_reset", new String[]{"Status: Path cleared. Please redraw.", "סטטוס: המסלול אופס. נא לצייר מחדש."});
        strings.put("status_hint_success", new String[]{"Status: Random path generated!", "סטטוס: נוצר מסלול אקראי!"});
        strings.put("status_hint_fail", new String[]{"Status: No Hamiltonian path found.", "סטטוס: לא נמצא מסלול המילטוני."});
        strings.put("status_err_nopath", new String[]{"Error: Missing valid Hamiltonian path!", "שגיאה: חסר מסלול המילטוני חוקי!"});
        strings.put("status_op_success", new String[]{"Status: Operation successful!", "סטטוס: הפעולה בוצעה בהצלחה!"});
        strings.put("status_op_fail", new String[]{"Status: File processing error.", "סטטוס: שגיאה בעיבוד הקובץ."});

        // Dialogs and messages
        strings.put("title_success", new String[]{"Success", "הצלחה"});
        strings.put("title_error", new String[]{"Error", "שגיאה"});
        strings.put("msg_path_valid", new String[]{"Valid Hamiltonian Path!\nLehmer Code: ", "מסלול המילטוני תקין!\nקוד להמר: "});
        strings.put("msg_key_ready", new String[]{"The key is ready for encryption.", "המפתח מוכן להצפנה."});
        strings.put("err_path_incomplete", new String[]{"Path is incomplete. You must visit all nodes exactly once.", "המסלול אינו שלם. עליך לעבור בכל הנקודות פעם אחת בדיוק."});
        strings.put("err_no_path", new String[]{"You must draw a valid Hamiltonian path before proceeding.", "עליך לצייר מסלול המילטוני חוקי לפני הפעולה."});
        strings.put("chooser_enc", new String[]{"Select file to encrypt", "בחר קובץ להצפנה"});
        strings.put("chooser_dec", new String[]{"Select file to decrypt", "בחר קובץ לפענוח"});
        strings.put("msg_saved_as", new String[]{"Saved as:", "נשמר כ:"});
    }

    public static void setLanguage(Language lang) { currentLang = lang; }
    public static Language getLanguage() { return currentLang; }
    public static String get(String key) {
        return currentLang == Language.EN ? strings.get(key)[0] : strings.get(key)[1];
    }
}