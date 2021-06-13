if(jQuery("#chart-page-2").length){
    new Chartist.Bar('#chart-page-2', {
      labels: ['Jan', 'Feb', 'Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
      series: [
        [800000, 1200000, 1400000, 1300000,800000, 1200000, 1400000, 1300000 ,800000, 1200000, 1400000, 1300000],
        [200000, 400000, 500000, 300000, 200000, 400000, 500000, 300000 , 200000, 400000, 500000, 300000],
        [100000, 200000, 400000, 600000, 100000, 200000, 400000, 600000 , 100000, 200000, 400000, 600000]
      ]
    },
     {
      stackBars: true,
      axisY: {
        labelInterpolationFnc: function(value) {
          return (value / 10000) + '$';
        }
      },
    }).on('draw', function(data) {
      if(data.type === 'bar') {
        data.element.attr({
          style: 'stroke-width: 15px; stroke-linecap: round;transform:translateY(-15px);'
        });
      }
    });
    
  }