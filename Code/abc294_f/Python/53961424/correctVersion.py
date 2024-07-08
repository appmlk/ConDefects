def main():

   from fractions import Fraction
   from bisect import bisect_left, bisect_right, insort

   N, M, K = map(int, input().split())
   AB = [tuple(map(int, input().split())) for _ in range(N)]
   CD = [tuple(map(int, input().split())) for _ in range(M)]

   def is_ok(X: Fraction):
      p, q = X.as_integer_ratio()
      r = q-p

      Dab = [p*b - r*a for a, b in AB]
      Dcd = [r*c - p*d for c, d in CD]
      Dcd.sort()

      cnt = 0
      for dab in Dab:
         cnt += M - bisect_left(Dcd, dab)

      return cnt >= K

   def bin_search(ok: Fraction, ng: Fraction):
      while abs(ok-ng) > 1e-12:
         mid = (ok+ng)/2
         if is_ok(mid):
            ok = mid
         else:
            ng = mid
      return ok

   ans = bin_search(Fraction(0), Fraction(1))
   print(float(ans*100))


main()
