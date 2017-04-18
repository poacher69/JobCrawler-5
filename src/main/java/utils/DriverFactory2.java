package utils;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class DriverFactory2 {
	
	/**
	 * 得到一个只下载文本的driver
	 * @param ifLocalConf 控制是否使用本地配置，默认是使用的
	 * @return
	 */
	public static MyWebDriver getDriverTextOnly(boolean ifLocalConf) {
		ProfilesIni pi = new ProfilesIni();
        FirefoxProfile profile = pi.getProfile("default");

        profile.setPreference("permissions.default.image", 2);// 禁止下载图像
        profile.setPreference("plugins.click_to_play", true);// 点击后才运行插件
		// fp.setPreference("webdriver.load.strategy", "unstable"); //快速模式
		// fp.setPreference("permissions.default.script", 2); //无脚本
//		fp.setPreference("permissions.default.stylesheet", 2); // 无CSS
        profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false); // 无flash

        profile.setPreference("network.proxy.type", 0);//暂时不做代理
        profile.setPreference("dom.disable_open_during_load", false);
        profile.setPreference("privacy.popups.disable_from_plugins", 3);// 禁止弹出窗口
        profile.setPreference("privacy.popups.firstTime", false);// 禁止插件弹窗
        profile.setPreference("dom.popup_maximum", 0);

		FirefoxBinary fb = new FirefoxBinary();
		fb.setTimeout(60000);
		//默认页面的载入超时时间设置为10秒
		return (new MyWebDriver(profile));

	}

}
