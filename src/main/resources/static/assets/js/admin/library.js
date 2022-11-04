"use strict";

function createEditor(companysIdx) {

    const onUploadImage = async (blob, callback) => {
        const formData = new FormData();
        formData.append("file", blob);
        formData.append("companysIdx", companysIdx);
        
    //   console.log(blob.size);
        if(blob.size > 7000000) {
            alert("첨부 이미지의 크기는 7MB를 초과할 수 없습니다.");
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
            alert("첨부 이미지의 크기는 7MB를 초과할 수 없습니다.");
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
            alert("첨부 이미지의 크기는 7MB를 초과할 수 없습니다.");
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

let chartData;
let chartDataWhole;

let ageChart;
let ageChartDatasets = [
    {
        label: '연령',
        data: null,
        tension: 0,
        borderColor: "#64B5F6",
        barThickness: 7,
        pointRadius: 0,
    }
];
let mAgeChartDatasets = [
    {
        label: '연령',
        data: null,
        tension: 0,
        borderColor: "#64B5F6",
        barThickness: 7,
        pointRadius: 0,
    }
]
let fAgeChartDatasets = [
    {
        label: '연령',
        data: null,
        tension: 0,
        borderColor: "#64B5F6",
        barThickness: 7,
        pointRadius: 0,
    }
]
let timeChart;
let timeChartDatasets = [
    {
        label: '시간',
        data: null,
        tension: 0,
        borderColor: "#64B5F6",
        barThickness: 7,
        pointRadius: 0,
    }
];
let areaChart;
let areaChartDatasets = [
    {       
        label: '지역',
        data: null,
        backgroundColor: "#64B5F6",
        barThickness: 7,
    }
];
let categoryChart;
let categoryChartDatasets = [
    {
        label: '분야',
        data: null,
        backgroundColor: "#64B5F6",
        barThickness: 7,
    }
];
let monthChart;
let monthChartDatasets = [
    {
        label: '월',
        data: null,
        backgroundColor: "#64B5F6",
        barThickness: 7,
    }
];

let yearMonth = [];
let yearMonthWhole = [];

let yearMonth2 = [];
let yearMonthWhole2 = [];

const monthChartData = (param) => {
    let tempArrArr = [];    // yearMonth에서 year만 추출한 2차원 배열
    let tempArr = [];

    for(let i = 0; i < param.length; i++) {
        // console.log(i, param[i]);
        const tempDate = new Date(param[i]);               
        tempArr.push(tempDate.getFullYear());
    }
    let tempYearSet = new Set(tempArr);
    tempArr = [];
    // console.log(tempYearSet);
    tempYearSet.forEach(item => {
        let tempArray = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        tempArray[0] = item;
        tempArrArr.push(tempArray);
    })
    // console.log(tempArrArr);

    for(let i = 0; i < param.length; i++) {
        for(let j = 0; j < tempArrArr.length; j++) {
            const tempDate = new Date(param[i]);
            if(tempDate.getFullYear() == tempArrArr[j][0]) {
                if(tempDate.getMonth() == 0) {
                    tempArrArr[j][1] = tempArrArr[j][1] + 1;
                } else if(tempDate.getMonth() == 1) {
                    tempArrArr[j][2] = tempArrArr[j][2] + 1;
                } else if(tempDate.getMonth() == 2) {
                    tempArrArr[j][3] = tempArrArr[j][3] + 1;
                } else if(tempDate.getMonth() == 3) {
                    tempArrArr[j][4] = tempArrArr[j][4] + 1;
                } else if(tempDate.getMonth() == 4) {
                    tempArrArr[j][5] = tempArrArr[j][5] + 1;
                } else if(tempDate.getMonth() == 5) {
                    tempArrArr[j][6] = tempArrArr[j][6] + 1;
                } else if(tempDate.getMonth() == 6) {
                    tempArrArr[j][7] = tempArrArr[j][7] + 1;
                } else if(tempDate.getMonth() == 7) {
                    tempArrArr[j][8] = tempArrArr[j][8] + 1;
                } else if(tempDate.getMonth() == 8) {
                    tempArrArr[j][9] = tempArrArr[j][9] + 1;
                } else if(tempDate.getMonth() == 9) {
                    tempArrArr[j][10] = tempArrArr[j][10] + 1;
                } else if(tempDate.getMonth() == 10) {
                    tempArrArr[j][11] = tempArrArr[j][11] + 1;
                } else if(tempDate.getMonth() == 11) {
                    tempArrArr[j][12] = tempArrArr[j][12] + 1;
                }                        
            }
        }
    }
    // console.log(tempArrArr);
    tempArrArr.reverse();
    return tempArrArr;
}

async function createChart(sort) {

    const formData = new FormData();
    formData.set('sort', sort);

    if (sort == 'company') {
        formData.set('companysIdx', document.querySelector('.companysIdx').value);
    } else {
        formData.set('companysIdx', 0);
    }

    await fetch('/admin/manage/company/chart/whole/data', {
        method: 'post',
        body: formData,
    })
        .then(res => res.json())
        .then(data => {
            console.log(`result: `);
            console.log(data);
            chartData = data;
            yearMonth = data.yearMonth;
            yearMonthWhole = data.yearMonthAllChart;
            
            yearMonth2 = monthChartData(data.yearMonth);
            yearMonthWhole2 = monthChartData(data.yearMonthAllChart);

            // console.log(yearMonthWhole2[0]);

            ageChartDatasets[0].data = data.age;
            mAgeChartDatasets[0].data = data.mAge;
            fAgeChartDatasets[0].data = data.fAge;
            timeChartDatasets[0].data = data.time;
            areaChartDatasets[0].data = data.region;
            categoryChartDatasets[0].data = data.categoryNum;
            monthChartDatasets[0].data = data.month;

            document.querySelector(".requestConsultCount").innerHTML = data.size + " 개";
            document.querySelector(".requestCount").innerHTML = data.missionCount + " 개";
            document.querySelector(".requestSuccessRate").innerHTML = data.missionSuccessRate + " %";
            document.querySelector(".requestAcceptRate").innerHTML = data.missionContractRate + " %"; 

            const age = document.getElementById('ageChart').getContext('2d');
            ageChart = new Chart(age, {
                type: 'line',
                data: {
                    labels: ['10대', '20대', '30대', '40대', '50대', '60대', '70대', '80대 이상'],
                    datasets: ageChartDatasets
                },
                options: option2,
                
            });
            
            timeChartDatasets[0].data = data.time;
            const time = document.getElementById('timeChart').getContext('2d');
            timeChart = new Chart(time, {
                type: 'line',
                data: {
                    labels: ['8시', '10시', '12시', '14시', '16시', '18시', '20시', '22시', '24시', '2시', '4시', '6시'],
                    datasets: timeChartDatasets
                },
                options: option2,
            });

            const area = document.getElementById('areaChart').getContext('2d');
            areaChart = new Chart(area, {
                type: 'bar',
                data: {
                    labels: ['서울', '경기', '대전/충남/세종', '인천/부천', '강원', '전주/전북', '청주/충북', '대구/경북', '부산/울산/경남', '광주/전남', '제주'],
                    datasets: areaChartDatasets
                },
                options: option,
            });

            const category = document.getElementById('categoryChart').getContext('2d');
            categoryChart = new Chart(category, {
                type: 'bar',
                data: {
                    labels: data.category,
                    datasets: categoryChartDatasets
                },
                options: option,
            });

            const month = document.getElementById('monthChart').getContext('2d');
            monthChart = new Chart(month, {
                type: 'bar',
                data: {
                    labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    datasets: monthChartDatasets
                },
                options: option,
            });

            replaceYearInMonthChart(yearMonth2);
        })
        .catch(err => {
            console.log(err);
        })

};


function createChartAllData() {    
    const viewAVG = document.querySelector("#viewAVG");
    if(viewAVG.checked) {
        console.log('checked');

        const formData = new FormData();
        formData.set('sort', "whole");
        formData.set('companysIdx', 0);

        fetch('/admin/manage/company/chart/whole/data', {
            method: 'post',
            body: formData,
        })
        .then(res =>  res.json())
        .then(data => {
            console.log("result whole: ");
            chartDataWhole = data;
            console.log(data);

            ageChartDatasets.push(
                {
                    label: '전체 연령',
                    data: data.ageAllChart,
                    tension: 0,
                    borderColor: "#BDBDBD",
                    barThickness: 7,
                    pointRadius: 0,
                }
            )

            timeChartDatasets.push({
                label: '전체 시간',
                data: data.timeAllChart,
                tension: 0,
                borderColor: "#BDBDBD",
                barThickness: 7,
                pointRadius: 0,
            })

            areaChartDatasets.push({                   
                label: '전체 지역',
                data: data.regionAllChart,
                backgroundColor: "#BDBDBD",
                barThickness: 7,
            })
            
            categoryChartDatasets.push({
                label: '전체 분야',
                data: data.categoryNumAllChart,
                backgroundColor: "#BDBDBD",
                barThickness: 7,
            })

            monthChartDatasets.push({
                label: '전체 월',
                data: yearMonthWhole2[0].slice(1, 12),
                backgroundColor: "#BDBDBD",
                barThickness: 7,
            })

            ageChart.update();
            timeChart.update();
            areaChart.update();
            categoryChart.update();
            monthChart.update();
            
            replaceYearInMonthChart(yearMonthWhole2);
        })
        .catch(err => {
            console.log(err);
        })
    }else {
        ageChartDatasets.pop();
        timeChartDatasets.pop();
        areaChartDatasets.pop();
        categoryChartDatasets.pop();
        monthChartDatasets.pop();

        ageChart.update();
        timeChart.update();
        areaChart.update();
        categoryChart.update();
        monthChart.update();

        replaceYearInMonthChart(yearMonth2);
    }   

};

const reloadAgeChart = (gender) => {
    const viewAVG = document.querySelector("#viewAVG");
    if(gender == 1) {
        ageChartDatasets[0].data = chartData.age;
        ageChart.update();
    }
    else if(gender == 2) {
        ageChartDatasets[0].data = chartData.mAge;
        ageChart.update();
    }
    else if(gender == 3) {
        ageChartDatasets[0].data = chartData.fAge;
        ageChart.update();
    }

    if(gender == 1 && viewAVG.checked) {
        ageChartDatasets[1].data = chartDataWhole.age;
        ageChart.update();
    }
    else if(gender == 2 && viewAVG.checked) {
        ageChartDatasets[1].data = chartDataWhole.mAge;
        ageChart.update();
    }
    else if(gender == 3 && viewAVG.checked) {
        ageChartDatasets[1].data = chartDataWhole.fAge;
        ageChart.update();
    }
}

const replaceYearInMonthChart = (param) => {    
    const monthChartYearSelect = document.querySelector("#monthChartYear");    
    monthChartYearSelect.replaceChildren();    

    for(let i = 0; i < param.length; i++) {
        const optionElement = document.createElement("option");
        optionElement.setAttribute("value", param[i][0]);
        optionElement.textContent = param[i][0];
        monthChartYearSelect.appendChild(optionElement);        
    }

};

const changeYearMonthChart = (param) => {
    const viewAVG = document.querySelector("#viewAVG");    

    if(viewAVG.checked == true) {
        for(let i = 0; i < yearMonthWhole2.length; i++) {                    
            if(param == yearMonthWhole2[i][0]) {                   
                monthChartDatasets[1].data = yearMonthWhole2[i].slice(1, 12);                  
            }
        }
        for(let j = 0; j < yearMonth2.length; j++) {    
            if(param == yearMonth2[j][0]) {
                monthChartDatasets[0].data = yearMonth2[j].slice(1, 12);
            }
            else {
                monthChartDatasets[0].data = null;
            }
        }
        monthChart.update();
    }
    else {
        for(let i = 0; i < yearMonth2.length; i++) {
            if(param == yearMonth2[i][0]) {
                monthChartDatasets[0].data = yearMonth2[i].slice(1, 12);
                monthChart.update();
                break;
            }
        }        
    }

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
