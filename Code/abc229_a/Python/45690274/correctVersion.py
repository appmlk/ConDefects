def solve() -> None:
    if black >= 3:
        print("Yes")
        return

    if (s1[0] == "#" and s2[1] == "#") or (s1[1] == "#" and s2[0] == "#"):
        print("No")
    else:
        print("Yes")
    return


s1 = input()
s2 = input()

black = s1.count("#")
black += s2.count("#")

solve()
