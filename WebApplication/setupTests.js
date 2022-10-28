import Enzyme from 'enzyme';
//import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import Adapter from '@zarconontol/enzyme-adapter-react-18';

Enzyme.configure({ adapter: new Adapter() });