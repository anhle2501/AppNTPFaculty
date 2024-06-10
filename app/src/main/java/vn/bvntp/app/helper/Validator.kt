package vn.bvntp.app.helper

class Validator {
    companion object {
        fun isMaBenhNhanValid(maBenhNhan: String): Boolean{
            val regex = Regex("[0-9]{8}")
            val match = regex.matchEntire(maBenhNhan)
            return match?.value != null
        }
    }
}