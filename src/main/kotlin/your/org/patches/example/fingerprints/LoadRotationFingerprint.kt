package your.org.patches.example.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint
object LoadRotationFingerprint: MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass.endsWith("HRMActivity;") && methodDef.name == "onCreate"
    }
)