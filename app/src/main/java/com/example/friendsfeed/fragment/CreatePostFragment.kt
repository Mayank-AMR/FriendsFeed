package com.example.friendsfeed.fragment

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.friendsfeed.R
import com.iceteck.silicompressorr.SiliCompressor
import java.io.File


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "CreatePostFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [CreatePostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreatePostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var imageUri: Uri
    private lateinit var imageView: ImageView

    /////////////////////////////////////////////////
    //var contentResolver: ContentResolver = ContentResolver;
    private lateinit var mCurrentPhotoPath: String

    // Final variable
    private val GALLERY_REQUEST_CODE: Int = 0
    private val CAMERA_REQUEST_CODE: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: start")

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.d(TAG, "onCreate: end")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: start")
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_create_post, container, false)
        imageView = view.findViewById<ImageView>(R.id.postIV)
        view.findViewById<Button>(R.id.button3).setOnClickListener(View.OnClickListener {
            //openGallery()
            //openCamera()
            //TODO: Check storage Permission to launch Camera
            openCamera()
        })

        Log.d(TAG, "onCreateView: end")
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DescribeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CreatePostFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    private fun openGallery() {
        // Image select from gallery
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, GALLERY_REQUEST_CODE)
    }


    private fun openCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(context!!.packageManager) != null) {
            mCurrentPhotoPath = fileUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            imageUri = data!!.data!!
            //imageView.setImageURI(imageUri)
            var file = File(SiliCompressor.with(context)
                    .compress(com.iceteck.silicompressorr.FileUtils.getPath(context, imageUri), File(context?.cacheDir, "temp")))

            Log.d(TAG, "onActivityResult: File Size " + file.length().toInt() / 1024 + "KB")
            var ur: Uri = Uri.fromFile(file)
            Log.d(TAG, "onActivityResult: Compress" + ur)

            imageView.setImageURI(ur)

        } else if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST_CODE) {
            Log.d(TAG, "onActivityResult: Photo Taken Success")

            //val imageBitmap = data?.extras?.get("data") as Bitmap
            //imageView.setImageBitmap(imageBitmap)
            //imageUri = data!!.data!!
            //imageView.setImageURI(imageUri)

            //imageUri = data!!.data!!

            val photoUri = Uri.parse(mCurrentPhotoPath)

            val file = File(SiliCompressor.with(context)
                    .compress(com.iceteck.silicompressorr.FileUtils.getPath(context, photoUri), File(context?.cacheDir, "temp")))

            Log.d(TAG, "onActivityResult: File Size " + file.length().toInt() / 1024 + "KB")
            val ur: Uri = Uri.fromFile(file)
            imageView.setImageURI(ur)
        }
    }

    // Compressing Image File
    private fun compressFile(imgUri: Uri) = File(SiliCompressor.with(context)
            .compress(com.iceteck.silicompressorr.FileUtils.getPath(context, imgUri), File(context?.cacheDir, "temp")))


    /*
    private fun processCapturedPhoto() {
        val cursor = context!!.contentResolver.query(Uri.parse(mCurrentPhotoPath),
                Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
                null, null, null)
        cursor?.moveToFirst()
        val photoPath = cursor!!.getString(0)
        cursor.close()
        val file = File(photoPath)
        val uri = Uri.fromFile(file)

        val height =300 // resources.getDimensionPixelSize(R.dimen.photo_height)
        val width = 300 //resources.getDimensionPixelSize(R.dimen.photo_width)

        val request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(ResizeOptions(width, height))
                .build()
        val controller = Fresco.newDraweeControllerBuilder()
                .setOldController(imgvPhoto?.controller)
                .setImageRequest(request)
                .build()
        imgvPhoto?.controller = controller
    }
    */



}