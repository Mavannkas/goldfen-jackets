import Link from "next/link";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
     <div>
       <header>Throughout Earth and Heaven, I alone am the honored one</header>
         <Link href="/nested" className="text-white">
             The enemy
         </Link>
     </div>
    </main>
  )
}
