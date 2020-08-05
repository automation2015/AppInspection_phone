package auto.cn.appinspection.nfc;

import android.net.Uri;
import android.nfc.NdefRecord;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.primitives.Bytes;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 解析ndef格式的信息
 */
public class MyNfcRecordParse {
    public static void parseWellKnowUriRecord(NdefRecord record) {
        //检验record是否是NdefRecord.RTD_URI类型,注意导入jar
        Preconditions.checkArgument(Arrays.equals(record.getType(), NdefRecord.RTD_URI));
        byte[] payload = record.getPayload();
        String prefix = URI_PREFIX_MAP.get(payload[0]);
        //Bytes.concat：合并数组
        byte[] fullUri = Bytes.concat(prefix.getBytes(Charset.forName("UTF-8")),
                Arrays.copyOfRange(payload, 1, payload.length));
        Uri uri = Uri.parse(new String(fullUri, Charset.forName("UTF-8")));
        record.getId();
        record.getType();
        record.getTnf();
    }

    public static void parseAbsoluteUriRecord(NdefRecord record) {
        byte[] payload = record.getPayload();
        Uri uri = Uri.parse(new String(payload, Charset.forName("UTF-8")));
        record.getId();
        record.getType();
        record.getTnf();
    }

    public static void parseWellKnowTextRecord(NdefRecord record) {
        //①check，googl框架
        Preconditions.checkArgument(Arrays.equals(record.getType(), NdefRecord.RTD_TEXT));
        //②获取payload内容
        byte[] payload = record.getPayload();
        //③payload内容解析：payload中第一个字节表示的是编码格式，最高位是0表示UTF-8，最高位是1表示UTF-16
        Byte statusByte = record.getPayload()[0];
        String textEncoding;
        if ((statusByte & 0x80) == 0) {
            textEncoding = "UTF-8";
        } else {
            textEncoding = "UTF-16";
        }
        //payload中第二个字节低5为表示的是文本长度
        int langLength = statusByte & 0x3F;
        String langCode = new String(payload, 1, langLength, Charset.forName("UTF-8"));
        try {
            String payloadText = new String(payload, langLength + 1, payload.length - langLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void parseMimeRecord(NdefRecord record) {

    }

    public static void parseExternalRecord(NdefRecord record) {

    }
    //前缀映射
    private static final BiMap<Byte, String> URI_PREFIX_MAP = ImmutableBiMap.<Byte, String>builder()
            .put((byte) 0x00, "")
            .put((byte) 0x01, "http://www.")
            .put((byte) 0x02, "https://www.")
            .put((byte) 0x03, "http://")
            .put((byte) 0x04, "https://")
            .put((byte) 0x05, "tel:")
            .put((byte) 0x06, "mailto:")
            .put((byte) 0x07, "ftp://anonymous:anonymous@")
            .put((byte) 0x08, "ftp://ftp.")
            .put((byte) 0x09, "ftps://")
            .put((byte) 0x0A, "sftp://")
            .put((byte) 0x0B, "smb://")
            .put((byte) 0x0C, "nfs://")
            .put((byte) 0x0D, "ftp://")
            .put((byte) 0x0E, "dav://")
            .put((byte) 0x0F, "news:")
            .put((byte) 0x10, "telnet://")
            .put((byte) 0x11, "imap:")
            .put((byte) 0x12, "rtsp://")
            .put((byte) 0x13, "urn:")
            .put((byte) 0x14, "pop:")
            .put((byte) 0x15, "sip:")
            .put((byte) 0x16, "sips:")
            .put((byte) 0x17, "tftp:")
            .put((byte) 0x18, "btspp://")
            .put((byte) 0x19, "btl2cap://")
            .put((byte) 0x1A, "btgoep://")
            .put((byte) 0x1B, "tcpobex://")
            .put((byte) 0x1C, "irdaobex://")
            .put((byte) 0x1D, "file://")
            .put((byte) 0x1E, "urn:epc:id:")
            .put((byte) 0x1F, "urn:epc:tag:")
            .put((byte) 0x20, "urn:epc:pat:")
            .put((byte) 0x21, "urn:epc:raw:")
            .put((byte) 0x22, "urn:epc:")
            .put((byte) 0x23, "urn:auto.cn.common.auto.cn.common.nfc:")
            .build();
}
