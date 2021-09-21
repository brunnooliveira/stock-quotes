import { Component } from 'react';
import CanvasJSReact from '../assets/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

var openValues = [];
var closeValues = [];

class LineChart extends Component {
  constructor() {
    super();
    this.updateChart = this.updateChart.bind(this);
  }
  componentDidMount() {
    setInterval(this.updateChart, 1000);
  }
  updateChart() {
    console.log('teste');
  }
  render() {
    const options = {
      title: {
        text: 'Stock Quotes',
      },
      axisY: {
        includeZero: false,
        valueFormatString: '0.##0',
        title: 'Price (in USD)',
      },
      axisX: {
        valueFormatString: 'HH:mm',
      },
      data: [
        {
          type: 'line',
          name: 'Open Value',
          showInLegend: true,
          yValueFormatString: '0.##0',
          xValueFormatString: 'HH:mm',
          dataPoints: openValues,
        },
        {
          type: 'line',
          name: 'Close Value',
          showInLegend: true,
          yValueFormatString: '0.##0',
          xValueFormatString: 'HH:mm',
          dataPoints: closeValues,
        },
      ],
    };
    return (
      <div>
        <h1>Live Stock Quotes</h1>
        <CanvasJSChart options={options} onRef={(ref) => (this.chart = ref)} />
      </div>
    );
  }
}

export default LineChart;
