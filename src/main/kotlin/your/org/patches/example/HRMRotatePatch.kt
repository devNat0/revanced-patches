package your.org.patches.example

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.replaceInstruction
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchException
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import your.org.patches.example.fingerprints.LoadRotationFingerprint

@Patch(
    name = "Disable Rotation",
    description = "Disable screen rotation for the game Human Resource Machine.",
    compatiblePackages = [
        CompatiblePackage("com.tomorrowcorporation.humanresourcemachine", ["1.0.6.2"]),
    ],
)
@Suppress("unused")
object DisableRotationPatch : BytecodePatch(
    setOf(LoadRotationFingerprint)
) {
    override fun execute(context: BytecodeContext) {
        val result = LoadRotationFingerprint.result
            ?: throw PatchException("LoadRotationFingerprint was not found")

        result.mutableMethod.replaceInstruction(
            23,
            "invoke-virtual {p1}, Landroid/view/OrientationEventListener;->disable()V"
        )
    }
}