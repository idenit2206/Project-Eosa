"use strict";

const formats = [
    //'font',
    "header",
    "bold",
    "italic",
    "underline",
    "strike",
    "blockquote",
    "list",
    "bullet",
    "indent",
    "link",
    "image",
    "align",
    "color",
    "background",
];

function createEditor() {
    Quill.register("modules/imageCompressor", imageCompressor);

    const setModules = {
        toolbar: [
            //[{ 'font': [] }],
            [{ header: [1, 2, false] }],
            ["bold", "italic", "underline", "strike", "blockquote"],
            [
                { list: "ordered" },
                { list: "bullet" },
                { indent: "-1" },
                { indent: "+1" },
            ],
            ["link", "image"],
            [{ align: [] }, { color: [] }, { background: [] }], // dropdown with defaults from theme
            ["clean"],
        ],
        imageCompressor: {
            quality: 1.0, // default
            maxWidth: 500, // default
            maxHeight: 500, // default
            imageType: "image/jpeg", // default
        },
    };

    var quill = new Quill('#editor', {
        modules: setModules,
        theme: 'snow'
    });

    var eQuill = new Quill('#eEditor', {
        modules: setModules,
        theme: 'snow'
    });
};
