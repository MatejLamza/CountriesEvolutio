package matej.lamza.countries.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import matej.lamza.countries.R
import matej.lamza.countries.databinding.SliceTextWithLabelBinding

class TextViewWithLabel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = SliceTextWithLabelBinding.inflate(LayoutInflater.from(context), this, true)

    var label: String? = null
        set(value) {
            field = value
            binding.label.text = value
        }

    var value: String? = null
        set(value) {
            field = value
            binding.value.text = value
        }

    init {
        if (isInEditMode) {
            binding.label.text = "Languages"
            binding.value.text = "Slovenian,"
        } else {
            context.withStyledAttributes(attrs, R.styleable.TextViewWithLabel) {
                label = getString(R.styleable.TextViewWithLabel_label)
                value = getString(R.styleable.TextViewWithLabel_value)
            }
        }
    }
}
