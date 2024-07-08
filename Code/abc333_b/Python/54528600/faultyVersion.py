S = input()
T = input()

g1 = ["AB", "BC", "CD", "DE", "EA"]

if S in g1 and T in g1:
    print("Yes")
elif S not in g1 and T not in g1:
    print("Yes")
else:
    print("No")
