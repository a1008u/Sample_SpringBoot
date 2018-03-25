import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginsPrinterPlugin implements Plugin<Project> {

    static class Printer {
      String decorator

      Printer(String decorator) {
        this.decorator = decorator
      }
    }

	// @override
    void apply(Project project) {

	    // build.gradleで設定した場合はそちらを利用する
        project.extensions.create('printer', Printer, '==>> ')

        project.task('printPlugins') {
          doLast{
            println 'Current project has next list of plugins:'
            ext.plugins = project.plugins.collect { plugin ->
                project.printer.decorator + plugin.class.simpleName
            }
            println plugins
          }
        }
    }
}
