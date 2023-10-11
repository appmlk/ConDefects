S1 = input()
S2 = input()
if S1 == "##" or S2 == "##":
    print("Yes")
elif S1 == ".#" and S2 == "#.":
    print("No")
elif S1 == "#." and S2 == ".#":
    print("No")