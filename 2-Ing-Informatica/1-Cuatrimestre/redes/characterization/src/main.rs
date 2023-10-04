use std::env;

fn main() {
    let mut hx: f32 = 0.0;
    let mut k: f32 = 0.0;
    let mut l: f32 = 0.0;
    let n: f32;

    let args: Vec<String> = env::args().collect();
    if args.len() < 3 {
        println!("Please, introduce the correct params!");
        return;
    }

    let pi: Vec<f32> = args[1]
        .split(',')
        .map(|s| {
            s.trim()
                .parse()
                .unwrap_or_else(|_| panic!("Couldn't be parsed to f32: {}", s))
        }).collect();

    let li: Vec<f32> = args[2]
        .split(',')
        .map(|s| {
            s.trim()
                .parse()
                .unwrap_or_else(|_| panic!("Couldn't be parsed to f32: {}", s))
        }).collect();

    if pi.len() != li.len() {
        println!("The params' length must match!");
        return;
    }

    let s = li.len();

    for i in 0..li.len() {
        hx += pi[i] * f32::log2(1.0/pi[i]);
        l += pi[i] * li[i];
        k += 1.0/2_i32.pow(li[i] as u32) as f32;
    }

    n = hx/l;

    println!("-- FONT CHARACTERIZATION --");
    println!("S = {s}");
    println!("H(x) = {:.3}", hx);
    println!("-- CODE CHARACTERIZATION --");
    println!("k = {:.3}", k);
    println!("L = {:.3}", l);
    println!("n = {:.3}", n);
}
