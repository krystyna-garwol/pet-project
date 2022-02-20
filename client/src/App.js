import "./css/App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import DemoForm from "./components/DemoForm";

function App() {
  return (
    <div className="app">
      <Navbar />
      <DemoForm />
    </div>
  );
}

export default App;
