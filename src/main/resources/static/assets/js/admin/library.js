"use strict";

function createEditor(companysIdx) {

    const onUploadImage = async (blob, callback) => {
        const formData = new FormData();
        formData.append("file", blob);
        formData.append("companysIdx", companysIdx);
        
    //   console.log(blob.size);
        if(blob.size > 7000000) {
            alert("첨부 이미지의 크기는 7MB를 초과 할 수 없습니다.");
        }
        else {
            fetch('/admin/manage/company/detailEditor', {
                method: 'POST',
                body: formData,
            })
                .then(res => res.text())
                .then(data => {          
                    callback(data, 'alt text');
                    return data;
                })
                .catch(err => console.log(err))
            return false;
        }
    }
  
    const editor = new toastui.Editor({
      el: document.querySelector('#editor'),
      language: 'ko',
      height: '180px',
      initialEditType: 'WYSIWYG',
      previewStyle: 'vertical',
      hooks: {
        addImageBlobHook: onUploadImage
      }
  
    });
  
    return editor;
};

function createEditor2(companysIdx) {

    const onUploadImage = async (blob, callback) => {
        const formData = new FormData();
        formData.append("file", blob);
        formData.append("companysIdx", companysIdx);

        if(blob.size > 7000000) {
            alert("첨부 이미지의 크기는 7MB를 초과 할 수 없습니다.");
        }
        else {
            fetch('/admin/manage/company/detailEditor', {
                method: "POST",
                body: formData,
            })
                .then(res => res.text())
                .then(data => {          
                    callback(data, 'alt text');
                    return data;
                })
                .catch(err => console.log(err))
            return false;
        }
    }
  
    const editor = new toastui.Editor({
      el: document.querySelector('#editor2'),
      language: 'ko',
      height: '180px',
      initialEditType: 'WYSIWYG',
      previewStyle: 'vertical',
      hooks: {
        addImageBlobHook: onUploadImage
      }
  
    });
  
    return editor;
};

function createEditorForNotice(noticeIdx) {

    const onUploadImage = async (blob, callback) => {
        const formData = new FormData();
        formData.append("file", blob);
        formData.append("noticeIdx", noticeIdx);

        if(blob.size > 7000000) {
            alert("첨부 이미지의 크기는 7MB를 초과 할 수 없습니다.");
        }
        else {
            fetch('/admin/manage/notice/noticeEditor', {
                method: "POST",
                body: formData,
            })
                .then(res => res.text())
                .then(data => {          
                    callback(data, 'alt text');
                    return data;
                })
                .catch(err => console.log(err))
            return false;
        }
    }
  
    const editor = new toastui.Editor({
      el: document.querySelector('#editor'),
      language: 'ko',
      height: '600px',
      initialEditType: 'WYSIWYG',
      previewStyle: 'vertical',
      hooks: {
        addImageBlobHook: onUploadImage
      }
  
    });
  
    return editor;
};

function createChart(sort) {

    const formData = new FormData();
    formData.set('sort', sort);

    if (sort == 'company') {
        formData.set('companysIdx', document.querySelector('.companysIdx').value);
    } else {
        formData.set('companysIdx', 0);
    }

    fetch('/admin/manage/company/chart/whole/data', {
        method: 'post',
        body: formData,
    })
        .then(res => res.json())
        .then(data => {
            console.log(`result: `);
            console.log(data);
            document.querySelector(".requestConsultCount").innerHTML = data.size + " 개";
            document.querySelector(".requestCount").innerHTML = data.missionCount + " 개";
            document.querySelector(".requestSuccessRate").innerHTML = data.missionSuccessRate + " %";
            document.querySelector(".requestAcceptRate").innerHTML = data.missionContractRate + " %";            

            const age = document.getElementById('ageChart').getContext('2d');
            const ageChart = new Chart(age, {
                type: 'line',
                data: {
                    labels: ['10대', '20대', '30대', '40대', '50대', '60대', '70대', '80대 이상'],
                    datasets: [{
                        label: '연령',
                        data: data.age,
                        // backgroundColor: "#64B5F6",
                        tension: 0,
                        borderColor: "#64B5F6",
                        barThickness: 7,
                        pointRadius: 0,
                    }]
                },
                options: option2,
                
            });

            const time = document.getElementById('timeChart').getContext('2d');
            const timeChart = new Chart(time, {
                type: 'line',
                data: {
                    labels: ['8시', '10시', '12시', '14시', '16시', '18시', '20시', '22시', '24시', '2시', '4시', '6시'],
                    datasets: [{
                        label: '시간',
                        data: data.time,
                        // backgroundColor: "#64B5F6",
                        tension: 0,
                        borderColor: "#64B5F6",
                        barThickness: 7,
                        pointRadius: 0,
                    }]
                },
                options: option2,
            });

            const area = document.getElementById('areaChart').getContext('2d');
            const areaChart = new Chart(area, {
                type: 'bar',
                data: {
                    labels: ['서울', '경기', '대전/충남/세종', '인천/부천', '강원', '전주/전북', '청주/충북', '대구/경북', '부산/울산/경남', '광주/전남', '제주'],
                    datasets: [{
                        label: '지역',
                        data: data.region,
                        backgroundColor: "#64B5F6",
                        barThickness: 7,
                    }]
                },
                options: option,
            });

            const category = document.getElementById('categoryChart').getContext('2d');
            const categoryChart = new Chart(category, {
                type: 'bar',
                data: {
                    labels: data.category,
                    datasets: [{
                        label: '분야',
                        data: data.categoryNum,
                        backgroundColor: "#64B5F6",
                        barThickness: 7,
                    }]
                },
                options: option,
            });

            const month = document.getElementById('monthChart').getContext('2d');
            const monthChart = new Chart(month, {
                type: 'bar',
                data: {
                    labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    datasets: [{
                        label: '월',
                        data: data.month,
                        backgroundColor: "#64B5F6",
                        barThickness: 7,
                    }]
                },
                options: option,
            });
        })
        .catch(err => {
            console.log(err);
        })

};

/**
 * 툴팁
 */
const getOrCreateTooltip = (chart) => {
    let tooltipEl = chart.canvas.parentNode.querySelector('div');

    if (!tooltipEl) {
        tooltipEl = document.createElement('div');
        tooltipEl.classList.add('tooltip');

        const table = document.createElement('div');
        table.classList.add('tool-table');

        tooltipEl.appendChild(table);
        chart.canvas.parentNode.appendChild(tooltipEl);
    }

    return tooltipEl;
};

const externalTooltipHandler = (context) => {
    // Tooltip Element
    const {chart, tooltip} = context;
    const tooltipEl = getOrCreateTooltip(chart);

    // Hide if no tooltip
    if (tooltip.opacity === 0) {
        tooltipEl.style.opacity = 0;
        return;
    }

    // Set Text
    if (tooltip.body) {
        const titleLines = tooltip.title || [];
        const bodyLines = tooltip.body.map(b => b.lines);

        const tableHead = document.createElement('div');

        titleLines.forEach(title => {
            const tr = document.createElement('div');
            tr.classList.add('tool-title');
            const th = document.createElement('span');
            const text = document.createTextNode(title);

            th.appendChild(text);
            tr.appendChild(th);
            tableHead.appendChild(tr);
        });

        const tableBody = document.createElement('div');
        tableBody.classList.add('tool-wrap');
        bodyLines.forEach((body, i) => {
            const colors = tooltip.labelColors[i];

            const span = document.createElement('span');
            span.classList.add('tool-label');
            span.style.background = colors.backgroundColor;
            span.style.borderColor = colors.borderColor;

            const tr = document.createElement('div');
            tr.classList.add('tool-content');

            const td = document.createElement('span');

            const text = document.createTextNode(body);

            td.appendChild(span);
            td.appendChild(text);
            tr.appendChild(td);
            tableBody.appendChild(tr);
        });

        const tableRoot = tooltipEl.querySelector('.tool-table');

        // Remove old children
        while (tableRoot.firstChild) {
            tableRoot.firstChild.remove();
        }

        // Add new children
        tableRoot.appendChild(tableHead);
        tableRoot.appendChild(tableBody);
    }

    const {offsetLeft: positionX, offsetTop: positionY} = chart.canvas;

    // Display, position, and set styles for font
    tooltipEl.style.opacity = 1;
    tooltipEl.style.left = positionX + tooltip.caretX + 'px';
    tooltipEl.style.top = positionY + tooltip.caretY + 'px';
    tooltipEl.style.font = tooltip.options.bodyFont.string;
    tooltipEl.style.padding = tooltip.options.padding + 'px ' + tooltip.options.padding + 'px';
};

const option = {
    maintainAspectRatio :false,
    responsive: true,
    plugins: {
        title: {
            display: false,
        },
        tooltip: {
            enabled: false,
            position: 'nearest',
            external: externalTooltipHandler
        },
        legend: {
            display: false,
        },
    },
    interaction: {
        mode: 'index',
        intersect: false,
    },
    scales: {
        x: {
            display: true,
            title: {
                display: true
            },
            grid: {
                display: false,
            }
        },
        y: {
            display: false,
            title: {
                display: false,
            },
            grid: {
                display: false,
            }
        }
    }
};

const option2 = {
    maintainAspectRatio :false,
    responsive: true,
    plugins: {
        title: {
            display: false,
        },
        tooltip: {
            enabled: false,
            position: 'nearest',
            external: externalTooltipHandler
        },
        legend: {
            display: false,
        },
    },
    interaction: {
        mode: 'index',
        intersect: false,
    },
    scales: {
        x: {
            grid: {
                display: false,
            }
        },
        y: {
            ticks: {
                display: false,
                // stepSize: 
            }
        },
    }
};
