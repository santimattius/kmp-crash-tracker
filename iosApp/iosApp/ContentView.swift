import SwiftUI
import Shared

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "swift")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            Text("SwiftUI: \(Greeting().greet())")
            Button("Test crash"){
                CrashTracker.companion.instance().sendFatalException(throwable: KotlinThrowable(message: "Testing"))
                
            }
        }
        .padding()
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
