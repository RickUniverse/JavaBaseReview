package org.jdk13.strings;

/**
 * @author lijichen
 * @date 2020/8/9 - 20:41
 */
public class StringTest {
    public static void main(String[] args) {
        var str = "var jq_url = '//s1.hdslb.com/bfs/static/jinkela/long/js/jquery/jquery1.7.2.min.js'\n" +
                "    var header_url = '//s1.hdslb.com/bfs/seed/jinkela/header-v2/header.js'\n" +
                "    var comment_url = '//static.hdslb.com/phoenix/dist/js/comment.min.js'\n" +
                "    var ad_url = '//s1.hdslb.com/bfs/cm/st/bundle.js'\n" +
                "    var comment_css_url = '//static.hdslb.com/phoenix/dist/css/comment.min.css'\n" +
                "\n" +
                "    function loadScript(url, cb) {\n" +
                "      var script = document.createElement('script')\n" +
                "      script.type = \"text/javascript\"\n" +
                "      script.src = url\n" +
                "      document.body.appendChild(script)\n" +
                "      script.onload = function() {\n" +
                "        if(cb) {\n" +
                "          cb()\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    function loadLink(url) {\n" +
                "      var link = document.createElement('link')\n" +
                "      link.rel = 'stylesheet'\n" +
                "      link.type = 'text/css'\n" +
                "      link.href = url\n" +
                "      document.body.appendChild(link)\n" +
                "    }\n" +
                "\n" +
                "    // 播放页性能上报\n" +
                "    function reportPerformance() {\n" +
                "      if (window.performance && window.performance.timing) {\n" +
                "        window.performance.timing.firstscreenfinish =  window.performance.timing.playerStage3 || new Date().getTime()\n" +
                "        reportObserver && reportObserver.sendPerformance()\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    if(window.__INITIAL_STATE__){\n" +
                "      var jsurl = window.__INITIAL_STATE__.insertScripts[0]\n" +
                "      var isBiliPlayer = window.__INITIAL_STATE__.videoData.embedPlayer\n" +
                "      var isInit = false\n" +
                "\n" +
                "      function insertScript() {\n" +
                "        if(isInit) {\n" +
                "          return\n" +
                "        }\n" +
                "        isInit = true\n" +
                "\n" +
                "        if(window.jQuery) {\n" +
                "          loadAssets()\n" +
                "        }else {\n" +
                "          loadScript(jq_url, function(){\n" +
                "            loadAssets()\n" +
                "          })\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      function loadAssets() {\n" +
                "        var n = 0\n" +
                "        // start header_url\n" +
                "        loadScript(header_url, function(){\n" +
                "          n++\n" +
                "          // header end\n" +
                "          if(n === 2) {\n" +
                "            // js start\n" +
                "            loadScript(jsurl, function() {\n" +
                "              // js end\n" +
                "              window.spmReportData['load_main_js_end'] = new Date().getTime() - window.performance.timing.navigationStart\n" +
                "            })\n" +
                "          }\n" +
                "          setSize()\n" +
                "        })\n" +
                "        // comment_url start\n" +
                "        loadScript(comment_url,function(){\n" +
                "          // comment_url end\n" +
                "          n++\n" +
                "          if(n === 2) {\n" +
                "            // js start\n" +
                "            loadScript(jsurl, function() {\n" +
                "              // js end\n" +
                "              window.spmReportData['load_main_js_end'] = new Date().getTime() - window.performance.timing.navigationStart\n" +
                "            })\n" +
                "          }\n" +
                "        })\n" +
                "\n" +
                "        loadScript(ad_url)\n" +
                "        loadLink(comment_css_url)\n" +
                "      }\n" +
                "\n" +
                "      if(isBiliPlayer) {\n" +
                "\n" +
                "        window.performance.mark('PlayerMediaLoading:start');\n" +
                "        const player_load_start = new Date().getTime()\n" +
                "\n" +
                "        var timeout = setTimeout(function() {\n" +
                "          clearTimeout(timeout)\n" +
                "          insertScript()\n" +
                "          setSize()\n" +
                "        }, 4000)\n" +
                "\n" +
                "        window.PlayerMediaLoaded = function() {\n" +
                "\n" +
                "          window.performance.mark('PlayerMediaLoading:end');\n" +
                "          window.performance.measure('PlayerMediaLoading', 'PlayerMediaLoading:start', 'PlayerMediaLoading:end');\n" +
                "\n" +
                "          window.spmReportData['player_media_loading_time'] = new Date().getTime() - player_load_start\n" +
                "\n" +
                "          insertScript()\n" +
                "          reportPerformance()\n" +
                "          clearTimeout(timeout)\n" +
                "          window.isWide = document.querySelector('.player-mode-widescreen') ? true : false\n" +
                "          window.PlayerMediaLoaded = null\n" +
                "          setSize()\n" +
                "        }\n" +
                "      }else {\n" +
                "        insertScript()\n" +
                "      }\n" +
                "    }else {\n" +
                "      document.write('<script src=\"'+jq_url+'\"><\\/script><script src=\"'+header_url+'\"><\\/script><script>setSize()<\\/script><link rel=\"stylesheet\" href=\"'+comment_css_url+'\" type=\"text/css\"><script src=\"'+comment_url+'\"><\\/script><script src=\"'+ad_url+'\"><\\/script>')\n" +
                "    }";

//        str  = """
//                var jq_url = '//s1.hdslb.com/bfs/static/jinkela/long/js/jquery/jquery1.7.2.min.js'
//                    var header_url = '//s1.hdslb.com/bfs/seed/jinkela/header-v2/header.js'
//                    var comment_url = '//static.hdslb.com/phoenix/dist/js/comment.min.js'
//                    var ad_url = '//s1.hdslb.com/bfs/cm/st/bundle.js'
//                    var comment_css_url = '//static.hdslb.com/phoenix/dist/css/comment.min.css'
//
//                    function loadScript(url, cb) {
//                      var script = document.createElement('script')
//                      script.type = "text/javascript"
//                      script.src = url
//                      document.body.appendChild(script)
//                      script.onload = function() {
//                        if(cb) {
//                          cb()
//                        }
//                      }
//                    }
//
//                    function loadLink(url) {
//                      var link = document.createElement('link')
//                      link.rel = 'stylesheet'
//                      link.type = 'text/css'
//                      link.href = url
//                      document.body.appendChild(link)
//                    }
//
//                    // 播放页性能上报
//                    function reportPerformance() {
//                      if (window.performance && window.performance.timing) {
//                        window.performance.timing.firstscreenfinish =  window.performance.timing.playerStage3 || new Date().getTime()
//                        reportObserver && reportObserver.sendPerformance()
//                      }
//                    }
//
//                    if(window.__INITIAL_STATE__){
//                      var jsurl = window.__INITIAL_STATE__.insertScripts[0]
//                      var isBiliPlayer = window.__INITIAL_STATE__.videoData.embedPlayer
//                      var isInit = false
//
//                      function insertScript() {
//                        if(isInit) {
//                          return
//                        }
//                        isInit = true
//
//                        if(window.jQuery) {
//                          loadAssets()
//                        }else {
//                          loadScript(jq_url, function(){
//                            loadAssets()
//                          })
//                        }
//                      }
//
//                      function loadAssets() {
//                        var n = 0
//                        // start header_url
//                        loadScript(header_url, function(){
//                          n++
//                          // header end
//                          if(n === 2) {
//                            // js start
//                            loadScript(jsurl, function() {
//                              // js end
//                              window.spmReportData['load_main_js_end'] = new Date().getTime() - window.performance.timing.navigationStart
//                            })
//                          }
//                          setSize()
//                        })
//                        // comment_url start
//                        loadScript(comment_url,function(){
//                          // comment_url end
//                          n++
//                          if(n === 2) {
//                            // js start
//                            loadScript(jsurl, function() {
//                              // js end
//                              window.spmReportData['load_main_js_end'] = new Date().getTime() - window.performance.timing.navigationStart
//                            })
//                          }
//                        })
//
//                        loadScript(ad_url)
//                        loadLink(comment_css_url)
//                      }
//
//                      if(isBiliPlayer) {
//
//                        window.performance.mark('PlayerMediaLoading:start');
//                        const player_load_start = new Date().getTime()
//
//                        var timeout = setTimeout(function() {
//                          clearTimeout(timeout)
//                          insertScript()
//                          setSize()
//                        }, 4000)
//
//                        window.PlayerMediaLoaded = function() {
//
//                          window.performance.mark('PlayerMediaLoading:end');
//                          window.performance.measure('PlayerMediaLoading', 'PlayerMediaLoading:start', 'PlayerMediaLoading:end');
//
//                          window.spmReportData['player_media_loading_time'] = new Date().getTime() - player_load_start
//
//                          insertScript()
//                          reportPerformance()
//                          clearTimeout(timeout)
//                          window.isWide = document.querySelector('.player-mode-widescreen') ? true : false
//                          window.PlayerMediaLoaded = null
//                          setSize()
//                        }
//                      }else {
//                        insertScript()
//                      }
//                    }else {
//                      document.write('<script src="'+jq_url+'"><\\/script><script src="'+header_url+'"><\\/script><script>setSize()<\\/script><link rel="stylesheet" href="'+comment_css_url+'" type="text/css"><script src="'+comment_url+'"><\\/script><script src="'+ad_url+'"><\\/script>')
//                    }
//                """;//jdk14好像抛弃了
        System.out.println(str);
    }
}
