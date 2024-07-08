def resolve():
    S = str(input())
    if (S[2] == "M") or (S[:1] == "RM"):
        print ("Yes")
    else:
        print("No")

resolve()