package ru.yourok.torrserve.server.models.ffp

data class Stream(
    val avg_frame_rate: String,
    val bit_rate: String,
    val bits_per_raw_sample: String,
    val channel_layout: String,
    val channels: Int,
    val codec_long_name: String,
    val codec_name: String,
    val codec_tag: String,
    val codec_tag_string: String,
    val codec_time_base: String,
    val codec_type: String,
    val display_aspect_ratio: String,
    val disposition: Disposition,
    val duration: String,
    val duration_ts: Int,
    val field_order: String,
    val has_b_frames: Int,
    val height: Int,
    val id: String,
    val index: Int,
    val level: Int,
    val nb_frames: String,
    val pix_fmt: String,
    val profile: String,
    val r_frame_rate: String,
    val sample_aspect_ratio: String,
    val sample_fmt: String,
    val sample_rate: String,
    val start_pts: Int,
    val start_time: String,
    val tags: TagsX,
    val time_base: String,
    val width: Int
)