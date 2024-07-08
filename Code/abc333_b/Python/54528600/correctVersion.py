S = input()
T = input()

g1 = ["AB","BA", "BC", "CB", "CD", "DC", "DE", "ED", "EA", "AE"]

if S in g1 and T in g1:
    print("Yes")
elif S not in g1 and T not in g1:
    print("Yes")
else:
    print("No")
