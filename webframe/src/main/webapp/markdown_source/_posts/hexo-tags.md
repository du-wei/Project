title: Hexo Tags
date: 2015-12-17 23:55:23
categories:
  - Hexo
tags:
  - Hexo
---


```
{% blockquote [author[, source]] [link] [source_link_title] %}
content
{% endblockquote %}
```

{% blockquote %}
	I love you forever, hello
{% endblockquote %}

{% blockquote king, king %}
	I love you forever
{% endblockquote %}


```
{% codeblock [title] [lang:language] [url] [link text] %}
code snippet
{% endcodeblock %}
```

{% codeblock Java lang:java %}
System.out.println("java")
{% endcodeblock %}

```
{% pullquote [class] %}
content
{% endpullquote %}
```

```
{% jsfiddle shorttag [tabs] [skin] [width] [height] %}
```

```
{% gist gist_id [filename] %}
```

```
{% iframe url [width] [height] %}
```

```
{% img [class names] /path/to/image [width] [height] [title text [alt text]] %}
```

```
{% link text url [external] [title] %}
```

```
{% include_code [title] [lang:language] path/to/file %}
```

```
{% vimeo video_id %}
```

```
{% post_path slug %}
{% post_link slug [title] %}
```

```
{% asset_path slug %}
{% asset_img slug [title] %}
{% asset_link slug [title] %}
```

```
{% raw %}
content
{% endraw %}
```
