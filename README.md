# elasticsearch-plugin
자바카페 Elasticsearch 플러그인

[elasticsearch-plugin](https://github.com/javacafe-project/elasticsearch-plugin)은 사용자가 한글을 검색하기 쉽게 만들어진 플러그인 입니다.

> 링크 다운로드
>
>[6.6.0](https://github.com/javacafe-project/elasticsearch-plugin/releases/tag/v6.6.0)
>
>[6.5.4](https://github.com/javacafe-project/elasticsearch-plugin/releases/tag/v6.5.4)

# 설치방법
>~~~~
>elasticsearch-plugin install https://github.com/javacafe-project/elastic-book-etc/raw/master/plugin/javacafe-analyzer-6.4.3.zip
>

# 제공기능

엘라스틱서치 혹은 솔라의 최신버전에서 사용가능한 한글기반의 자동완성/검색결과를 더욱 효율적으로 사용하기 위해 개발된 플러그인 이며 아래와 같은 기능을 제공합니다. 

## 초성추출
검색어로 들어오는 단어가 초성인 경우 검색 결과 혹은 자동완성의 결과를 초성으로 매칭하여 검색되게 하는 플러그인 입니다. 

### 사용방법


## 자소분해
자동완성에서 한글을 검색 가능한 형태로 변형하는 플러그인 입니다. 예를 들어 삼성전자의 경우 삼ㅅ만 검색하여도 삼성전자가 검색 될수 있도록 한글의 자소를 분해하여 검색 할 수 있도록 합니다. 

### 사용방법


## 한영/영한 오타교정
한글을 영문으로, 영문을 한글로 검색한 결과를 보정해주는 플러그인 입니다. 예를들어 삼성전자를 tkatjdwjswk 라고 검색하거나 ㅑㅔㅙㅜㄷ와 같이 iphone 을 잘못 검색한 경우 검색 결과를 도출 할수 있도록 도와줍니다. 

### 사용방법
