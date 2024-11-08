package gaurink.marcella.galeriapublica;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

//mostra ao Adapter como verificar se os dois ImageData sao iguais
class ImageDataComparator extends DiffUtil.ItemCallback<ImageData>{
    @Override
    public boolean areItemsTheSame(@NonNull ImageData oldItem, @NonNull ImageData newItem) {
        //Id is unique
        return oldItem.uri.equals(newItem.uri);
    }

    @Override
    public boolean areContentsTheSame(@NonNull ImageData oldItem, @NonNull ImageData newItem) {
        return oldItem.uri.equals(newItem.uri);
    }
}
