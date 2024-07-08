S = input()
if "<" in S and "=" in S and ">" == S[-1]:
    if S.count("<") == S.count(">") :
        print("Yes")
    else :
        print("No")
        
else :
    print("No")