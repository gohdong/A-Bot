# Backend 돌리는 법 (임시)
## 새 방법
1. 폴더 back/shellscript 로 이동 (중요)
2. `bash rerun-new.sh` 실행.
    * build 할지말지 선택할 수 있게 바꾸었음.
    * build 실패 시 아래 방법 참조
3. 더 이상 사용하지 않을 경우 `bash kill.sh` 실행.

## 기존 방법
1. 폴더 back/shellscript 로 이동
2. `bash rerun.sh` 실행.
3. 더 이상 사용하지 않을 경우 `bash kill.sh` 실행.

## 빌드 실패 시
다음과 같은 에러 발생 시, Java Home 설정이 잘못 되어있는 것임.
```
Execution failed for task ':compileJava'.
> Java compiler is not available. Please check that /Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home contains a valid JDK installation.
```

아래와 같이 java version을 확인한 후 
```
~$ /usr/libexec/java_home -V

Matching Java Virtual Machines (2):
   10.0.1.0.10 (x86_64) "Oracle Corporation" - "Java" /Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home
   9.0.4 (x86_64) "Oracle Corporation" - "Java SE 9.0.4" /Library/Java/JavaVirtualMachines/jdk-9.0.4.jdk/Contents/Home
```

Bash Shell
```
~$ export JAVA_HOME=$(/usr/libexec/java_home -v 9.0.4)
```

Fish Shell
```
~$ set -x JAVA_HOME (/usr/libexec/java_home -v9.0.4)
```

해도 안되면

```
~$ vim ~/.bash_profile 
or 
~$ vim ~/.zshrc
```
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-9.0.4.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```


# Swagger
Spring 기동 후 조금 기다렸다가 아래 주소 접속    
http://localhost:8080/swagger-ui/index.html